package br.tjce.demoiselleshiro.persistence;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.tjce.demoiselleshiro.domain.Sistema;

public class SistemaDAO extends JPACrud<Sistema, Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Transactional
	public boolean checkIfExit(String codigo){
		try{
			Query query = createQuery("SELECT s FROM Sistema s WHERE s.codigo = :codigo");
			query.setParameter("codigo", codigo);
			
			return (query.getResultList().size() == 0) ? false : true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
