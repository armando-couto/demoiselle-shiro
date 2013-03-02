package br.tjce.demoiselleshiro.persistence;

import java.io.Serializable;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.tjce.demoiselleshiro.domain.Entidade;

public class EntidadeDAO extends JPACrud<Entidade, Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;

//	@SuppressWarnings("unchecked")
//	public List<Entidades> selectAll(){
//		SelectQuery query = new SelectQuery(Entidades.class);
//		return performQuery(query);
//	}
//	
	
}
