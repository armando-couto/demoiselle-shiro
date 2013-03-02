package br.tjce.demoiselleshiro.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.tjce.demoiselleshiro.domain.Perfis;
import br.tjce.demoiselleshiro.persistence.PerfisDAO;

@BusinessController
public class PerfisBC extends DelegateCrud<Perfis, Integer, PerfisDAO> {

	private static final long serialVersionUID = 1L;

}
