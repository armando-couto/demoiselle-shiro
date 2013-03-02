package br.tjce.demoiselleshiro.persistence;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.tjce.demoiselleshiro.business.UsuariosBC;
import br.tjce.demoiselleshiro.domain.Usuario;

public class UsuarioDAO extends JPACrud<Usuario, Integer> {

	private static final long serialVersionUID = 1L;
	
	public boolean checkIfLoginExist(String userlogin){
		try{
			Query query = createQuery("SELECT u FROM Usuario u WHERE u.userlogin = :userlogin");
			query.setParameter("userlogin", userlogin);
			return (query.getResultList().isEmpty()) ? false : true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public Usuario loadByLogin(String userlogin){
		Query query = createQuery("SELECT u FROM Usuario u WHERE u.userlogin = :userlogin");
		query.setParameter("userlogin", userlogin);
		return (!query.getResultList().isEmpty()) ?  (Usuario) query.getSingleResult() : null;
	}
	
}
