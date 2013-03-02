package br.tjce.demoiselleshiro.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.tjce.demoiselleshiro.domain.Erro;
import br.tjce.demoiselleshiro.domain.Sistema;
import br.tjce.demoiselleshiro.persistence.ConsultaDAO;

@BusinessController
public class ConsultaBC extends DelegateCrud<Erro, Long, ConsultaDAO>{
	
	private static final long serialVersionUID = 1L;

	public Sistema loadSistemaByCode(String codigo){
		return getDelegate().loadSistemaByCode(codigo);
	}
	
	public Erro loadErroByCode(Integer code, Sistema sistema) {
		return getDelegate().loadErroByCode(code, sistema);
	}
	
}