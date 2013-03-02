package br.tjce.demoiselleshiro.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.tjce.demoiselleshiro.domain.Permissoes;
import br.tjce.demoiselleshiro.persistence.PermissoesDAO;

@BusinessController
public class PermissoesBC extends DelegateCrud<Permissoes, Integer, PermissoesDAO> {

	private static final long serialVersionUID = 1L;

}
