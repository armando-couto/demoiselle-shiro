package br.tjce.demoiselleshiro.business;

import java.util.Date;
import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.tjce.demoiselleshiro.domain.Erro;
import br.tjce.demoiselleshiro.domain.Logs;
import br.tjce.demoiselleshiro.domain.MaisConsultado;
import br.tjce.demoiselleshiro.domain.Sistema;
import br.tjce.demoiselleshiro.persistence.ReportDAO;

@BusinessController
public class ReportBC extends DelegateCrud<Logs, Integer, ReportDAO>{

	private static final long serialVersionUID = 1L;
	
	
	public List<MaisConsultado> getRelatorioMaisConsultados(Sistema sistema,Erro erro,Date dataInicio,Date dataFim){
		return getDelegate().getRelatorioMaisConsultados(sistema,erro,dataInicio,dataFim);
	}
	
}
