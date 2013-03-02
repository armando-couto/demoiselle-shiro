package br.tjce.demoiselleshiro.business;

import java.util.Date;

import org.apache.shiro.SecurityUtils;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.tjce.demoiselleshiro.constant.Acoes;
import br.tjce.demoiselleshiro.domain.Loggable;
import br.tjce.demoiselleshiro.domain.LogsAcoes;
import br.tjce.demoiselleshiro.domain.Usuario;
import br.tjce.demoiselleshiro.persistence.LogsAcoesDAO;

@BusinessController
public class LogsAcoesBC extends DelegateCrud<LogsAcoes, Long, LogsAcoesDAO> {

	private static final long serialVersionUID = 1L;

	public Usuario getUsuario(){
		String usuarioLogin = SecurityUtils.getSubject().getPrincipal().toString();
		return getDelegate().getUserByLogin(usuarioLogin);
	}
	
	@Transactional
	public void gravarLogAcao(Acoes acoes, String entidade, Loggable entityLog) {
		String realizado = "";
		if(acoes.equals(Acoes.INSERIR))
			realizado = "Inseriu em " + entityLog.getInfo();
		if(acoes.equals(Acoes.EDITAR))
			realizado = "Editou " + entityLog.getInfo();
		if(acoes.equals(Acoes.DELETAR))
			realizado = "Deletou registro do dominio " + entityLog.getInfo();
		
		String permissao = acoes.toString();
		try {
			if (SecurityUtils.getSubject().isAuthenticated()) {
				Usuario usuario = getUsuario();
				this.insert(new LogsAcoes(new Date(),
						realizado,
						entidade,
						usuario.getId(),
						permissao,
						usuario.getPerfis().get(0).getPerfil(),
						realizado,
						usuario.getUsername()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
