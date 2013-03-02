package br.tjce.demoiselleshiro.persistence;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.tjce.demoiselleshiro.domain.Erro;
import br.tjce.demoiselleshiro.domain.Sistema;



public class ErroDAO extends JPACrud<Erro, Long> {

	private static final long serialVersionUID = 1L;

//	public Erros load(Integer id) {
//		try {
//			if (id == null || id == 0) {
//				return new Erros();
//			}
//			Erros erros = getObjectForPK(Erros.class, id);
//			if (erros != null) {
//				return erros;
//			} else {
//				return new Erros();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new Erros();
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Erros> selectAll() {
//		SelectQuery query = new SelectQuery(Erros.class);
//		return performQuery(query);
//	}
//
//	public void save(Erros erros) {
//		try {
//			if (erros.getObjectContext() == null) {
//				registerNewObject(erros);
//			}
//			erros.getObjectContext().commitChanges();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public void delete(Integer id) {
//		try {
//			deleteObject((DataObject) load(id));
//			commitChanges();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
	
	public boolean checkIfCodeExist(Integer code, Sistema sistema) {
		try {
			
			Query query = createQuery("SELECT e FROM Erro e WHERE e.codigo = :codigo AND e.sistema.id = :sistema");
			query.setParameter("codigo", code);
			query.setParameter("sistema", sistema.getId());
			
			return (query.getResultList().isEmpty()) ? false : true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public boolean checkIfCodeExist(Integer code, Sistema sistema , Integer iderro) {
		try {
			
			Query query = createQuery("SELECT e FROM Erro e WHERE e.codigo = :codigo  AND e.sistema.id = :sistema AND e.id != :iderro");
			query.setParameter("codigo", code);
			query.setParameter("sistema", sistema.getId());
			query.setParameter("iderro", iderro);
						
			return (query.getResultList().isEmpty()) ? false : true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
