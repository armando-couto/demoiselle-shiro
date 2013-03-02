package br.tjce.demoiselleshiro.persistence;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.tjce.demoiselleshiro.domain.Erro;
import br.tjce.demoiselleshiro.domain.Sistema;

@PersistenceController
public class ConsultaDAO extends JPACrud<Erro, Long> {

	private static final long serialVersionUID = 1L;

	public Sistema loadSistemaByCode(String codigo) {
		try {
			Query query = createQuery("SELECT s FROM Sistema s WHERE s.codigo = :codigo");
			query.setParameter("codigo", codigo);

			return (query.getResultList().size() == 1) ? (Sistema) query
					.getSingleResult() : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// public Sistemas loadSistemaByCode(String code){
	// Expression expression = Expression.fromString(Sistemas.CODIGO_PROPERTY +
	// " = '"+ code +"'");
	// SelectQuery query = new SelectQuery(Sistemas.class,expression);
	//
	// @SuppressWarnings("unchecked")
	// List<Sistemas> result = (List<Sistemas>) performQuery(query);
	//
	// return (result.size() > 0) ? result.get(0) : null;
	// }
	
	public Erro loadErroByCode(Integer code, Sistema s) {
		try {
			Query query = createQuery("SELECT e FROM Erro e WHERE e.codigo = :codigo AND e.sistema.id = :sistema");
			query.setParameter("codigo", code);
			query.setParameter("sistema", s.getId());
			if (!query.getResultList().isEmpty()) {
				return  (Erro) query.getSingleResult();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
