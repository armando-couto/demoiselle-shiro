package br.tjce.demoiselleshiro.persistence;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.tjce.demoiselleshiro.domain.LogsAcoes;
import br.tjce.demoiselleshiro.domain.Usuario;

@PersistenceController
public class LogsAcoesDAO extends JPACrud<LogsAcoes, Long> {

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
