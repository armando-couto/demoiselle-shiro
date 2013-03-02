package br.tjce.demoiselleshiro.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.tjce.demoiselleshiro.constant.Acoes;
import br.tjce.demoiselleshiro.domain.Usuario;
import br.tjce.demoiselleshiro.persistence.UsuarioDAO;

@BusinessController
public class UsuariosBC extends DelegateCrud<Usuario, Integer, UsuarioDAO>{

	private static final long serialVersionUID = 1L;

	@Inject
	private LogsAcoesBC logsAcoesBC;
	
	public boolean checkIfLoginExist(String userlogin){
		return getDelegate().checkIfLoginExist(userlogin);
	}
	
	public Usuario loadByLogin(String userlogin){
		return getDelegate().loadByLogin(userlogin);
	}
	
	@Override
	public void insert(Usuario bean) {		
		super.insert(bean);
		logsAcoesBC.gravarLogAcao(Acoes.INSERIR, "Usuario", bean);
	}
	
	@Override
	public void update(Usuario bean) {
//		TempBean tmp = load(bean.)
//		if(bean.getUserpassword() == ""){
//			bean.setUserpassword(load(bean.getId()).getUserpassword());
//		}
		
		logsAcoesBC.gravarLogAcao(Acoes.EDITAR, "Usuario", bean);
		super.update(bean);
	}
	
	@Override
	public void delete(Integer id) {
		logsAcoesBC.gravarLogAcao(Acoes.DELETAR, "Usuario", load(id));
		super.delete(id);
	}
	
	
	
}
