package br.tjce.demoiselleshiro.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.tjce.demoiselleshiro.constant.Acoes;
import br.tjce.demoiselleshiro.domain.Erro;
import br.tjce.demoiselleshiro.domain.Sistema;
import br.tjce.demoiselleshiro.persistence.ErroDAO;

@BusinessController
public class ErrosBC extends DelegateCrud<Erro, Long, ErroDAO> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LogsAcoesBC logsAcoesBC;
	
	public boolean checkIfCodeExist(Integer code, Sistema s) {
		return getDelegate().checkIfCodeExist(code, s);
	}
	
	public boolean checkIfCodeExist(Integer code, Sistema sistema , Integer iderro) {
		return getDelegate().checkIfCodeExist(code, sistema, iderro);
	}
	
	@Override
	public void delete(Long id) {
		logsAcoesBC.gravarLogAcao(Acoes.DELETAR, "Erro", load(id));
		super.delete(id);
	}
	
	@Override
	public void insert(Erro bean) {
		super.insert(bean);
		logsAcoesBC.gravarLogAcao(Acoes.INSERIR, "Erro", bean);
	}
	
	@Override
	public void update(Erro bean) {
		logsAcoesBC.gravarLogAcao(Acoes.EDITAR, "Erro", bean);
		super.update(bean);
	}
	
}
