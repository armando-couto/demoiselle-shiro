package br.tjce.demoiselleshiro.security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.JdbcUtils;
import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.util.Beans;

public class MyJDBCRealm extends JdbcRealm {

//	private Connection connection;
	
	private Logger log;
	
//	private DataSource ds;
	
//	private EntityManagerFactory factory ;

	public MyJDBCRealm() {
//		factory = Persistence.createEntityManagerFactory("demoiselleshiro");
//		factory.createEntityManager().unwrap(Connection.class);
		
//		DatabaseMapping
		
        
        
//		em.close();
		
		log = Beans.getReference(Logger.class);
	}
	
	private Connection getConnection() throws SQLException{
//		EntityManager em = factory.createEntityManager();
//		em.getTransaction().begin();
//		connection = em.unwrap(Connection.class);
//		em.getTransaction().commit();
//		em.close();
	
		return 	DriverManager.getConnection("jdbc:mysql://localhost:3306/banco_erros_cayenne?user=root");
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();

		if (username == null) {
			throw new AccountException(
					"Null usernames are not allowed by this realm.");
		}

		Connection conn = null;
		SimpleAuthenticationInfo info = null;
		try {
			
//			System.out.println("GO AUTENTICATE " + username);
			conn = getConnection(); // dataSource.getConnection();

			String password = null;
			password = getPasswordForUser(conn, username)[0];
			
//			System.out.println("GO AUTENTICATE " + username + " PASSWORD " + password);
			
			if (password == null) {
				throw new UnknownAccountException("No account found for user ["
						+ username + "]");
			}
			//
			info = new SimpleAuthenticationInfo(username,
					password.toCharArray(), getName());

		} catch (SQLException e) {
			final String message = "There was a SQL error while authenticating user ["
					+ username + "]";
			if (log.isErrorEnabled()) {
				log.error(message, e);
			}

			throw new AuthenticationException(message, e);
		}finally{
			JdbcUtils.closeConnection(conn);
		}

		return info;
	}

	private String[] getPasswordForUser(Connection conn, String username)
			throws SQLException {

		String[] result = new String[1];
		boolean returningSeparatedSalt = false;

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(authenticationQuery);
			ps.setString(1, username);

			// Execute query
			rs = ps.executeQuery();

			// Loop over results - although we are only expecting one result,
			// since usernames should be unique
			boolean foundResult = false;
			while (rs.next()) {

				// Check to ensure only one row is processed
				if (foundResult) {
					throw new AuthenticationException(
							"More than one user row found for user ["
									+ username + "]. Usernames must be unique.");
				}

				result[0] = rs.getString(1);
				if (returningSeparatedSalt) {
					result[1] = rs.getString(2);
				}

				foundResult = true;
			}
		} catch (Exception e) {
			
		}

		return result;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		// null usernames are invalid
		if (principals == null) {
			throw new AuthorizationException(
					"PrincipalCollection method argument cannot be null.");
		}

		String username = (String) getAvailablePrincipal(principals);

		Connection conn = null;
		Set<String> roleNames = null;
		Set<String> permissions = null;
		try {
			conn =  getConnection();
			// Retrieve roles and permissions from database
			roleNames = getRoleNamesForUser(conn, username);

			if (permissionsLookupEnabled) {
				permissions = getPermissions(conn, username, roleNames);

//				System.out.println(permissions.toString());
			}

		} catch (SQLException e) {
			final String message = "There was a SQL error while authorizing user ["
					+ username + "]";
			if (log.isErrorEnabled()) {
				log.error(message, e);
			}

			// Rethrow any SQL errors as an authorization exception
			throw new AuthorizationException(message, e);
		} finally {
			JdbcUtils.closeConnection(conn);
		}

		// PermissionsAuthorizationFilter
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
		info.setStringPermissions(permissions);
		return info;

	}

	protected Set<String> getRoleNamesForUser(Connection conn, String username)
			throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<String> roleNames = new LinkedHashSet<String>();
		try {
			ps = conn.prepareStatement(userRolesQuery);
			ps.setString(1, username);
			
			// Execute query
			rs = ps.executeQuery();

			// Loop over results and add each returned role to a set
			while (rs.next()) {

				String roleName = rs.getString(1);

				// Add the role to the list of names if it isn't null
				if (roleName != null) {
					roleNames.add(roleName);
				} else {
					if (log.isWarnEnabled()) {
						log.warn("Null role name found while retrieving role names for user ["
								+ username + "]");
					}
				}
			}
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(ps);
		}
		return roleNames;
	}
	
	@Override
	protected Set<String> getPermissions(Connection conn, String username,
			Collection<String> roleNames) throws SQLException {
		 PreparedStatement ps = null;
	        Set<String> permissions = new LinkedHashSet<String>();
//	        System.out.println("CHECANDO PERMISSÕES ");
	        try {
	            ps = conn.prepareStatement(permissionsQuery);
	            for (String roleName : roleNames) {
	            	
	            	ps.setString(1, username);
	            	
	                ps.setString(2, roleName);
	                
	                ps.setString(3, username);
	            	
	                ps.setString(4, roleName);

	                ResultSet rs = null;

	                try {
	                    // Execute query
	                    rs = ps.executeQuery();

	                    // Loop over results and add each returned role to a set
	                    while (rs.next()) {

	                        String permissionString = rs.getString(1);

	                        // Add the permission to the set of permissions
	                        if(!permissions.contains(permissionString))
	                        	permissions.add(permissionString);
	                    }
	                } finally {
	                    JdbcUtils.closeResultSet(rs);
	                }
	            }
	        } finally {
	            JdbcUtils.closeStatement(ps);
	        }
//	        System.out.println("PERMISSOES " + permissions.toString());
	        return permissions;
//		return super.getPermissions(conn, username, roleNames);
	}
	
//	protected Set<String> getPermissions(Connection conn, String username)
//			throws SQLException {
//		PreparedStatement ps = null;
//		Set<String> permissions = new LinkedHashSet<String>();
//		try {
//			ps = conn.prepareStatement(permissionsQuery);
//
//			ps.setString(1, username);
//
//			ResultSet rs = null;
//
//			try {
//				// Execute query
//				rs = ps.executeQuery();
//
//				// Loop over results and add each returned role to a set
//				
////				System.out.println(username);
////				rs.getString(1);
//				
//				
//				String permissionString = null;
//				
//				while (rs.next()) {
//
//					permissionString = rs.getString(1);
//
//					// Add the permission to the set of permissions
//					permissions.add(permissionString);
//				}
//			} finally {
//				JdbcUtils.closeResultSet(rs);
//			}
//		} finally {
//			JdbcUtils.closeStatement(ps);
//		}
//
//		return permissions;
//	}

}
