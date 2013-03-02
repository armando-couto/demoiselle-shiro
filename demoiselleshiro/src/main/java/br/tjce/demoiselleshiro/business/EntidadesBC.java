package br.tjce.demoiselleshiro.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.tjce.demoiselleshiro.domain.Entidade;
import br.tjce.demoiselleshiro.persistence.EntidadeDAO;

@BusinessController
public class EntidadesBC extends DelegateCrud<Entidade, Integer, EntidadeDAO> {

	private static final long serialVersionUID = 1L;

}
