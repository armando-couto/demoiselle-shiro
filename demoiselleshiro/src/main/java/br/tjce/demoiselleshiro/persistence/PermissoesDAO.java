package br.tjce.demoiselleshiro.persistence;

import java.io.Serializable;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.tjce.demoiselleshiro.domain.Permissoes;

public class PermissoesDAO extends JPACrud<Permissoes, Integer> implements Serializable{
	
	private static final long serialVersionUID = 1L;

//	@SuppressWarnings("unchecked")
//	public List<Permissoes> selectAll(){
//		SelectQuery query = new SelectQuery(Permissoes.class);
//		return performQuery(query);
//	}
	
}
