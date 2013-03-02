package br.tjce.demoiselleshiro.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.tjce.demoiselleshiro.constant.Acoes;
import br.tjce.demoiselleshiro.domain.Sistema;
import br.tjce.demoiselleshiro.persistence.SistemaDAO;

@BusinessController
public class SistemasBC extends DelegateCrud<Sistema, Integer, SistemaDAO>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LogsAcoesBC logsAcoesBC;
	
	public boolean checkIfExist(String codigo){
		return getDelegate().checkIfExit(codigo);
	}
	
	@Override
	public void insert(Sistema bean) {
		super.insert(bean);
		logsAcoesBC.gravarLogAcao(Acoes.INSERIR, "Sistema", bean);
	}
	
	@Override
	public void update(Sistema bean) {
		super.update(bean);
		logsAcoesBC.gravarLogAcao(Acoes.EDITAR, "Sistema", bean);
	}
	
	@Override
	public void delete(Integer id) {		
		logsAcoesBC.gravarLogAcao(Acoes.DELETAR, "Sistema", load(id));
		super.delete(id);
	}
}
