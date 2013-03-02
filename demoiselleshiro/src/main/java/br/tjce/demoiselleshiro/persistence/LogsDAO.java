package br.tjce.demoiselleshiro.persistence;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.tjce.demoiselleshiro.domain.Logs;
import br.tjce.demoiselleshiro.domain.Usuario;


public class LogsDAO extends JPACrud<Logs, Long> {

	private static final long serialVersionUID = 1L;

	public Usuario getUserByLogin(String userlogin){
		try{
			Query query = createQuery("SELECT u FROM Usuario u WHERE u.userlogin = :userlogin");
			query.setParameter("userlogin", userlogin);
			if(!query.getResultList().isEmpty())
				return (Usuario)query.getSingleResult();
			else
				return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;		
	}
	
}
