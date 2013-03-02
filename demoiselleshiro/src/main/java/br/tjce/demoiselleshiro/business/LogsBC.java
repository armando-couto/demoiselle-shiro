package br.tjce.demoiselleshiro.business;

import java.util.Date;

import org.apache.shiro.SecurityUtils;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.tjce.demoiselleshiro.domain.Erro;
import br.tjce.demoiselleshiro.domain.Logs;
import br.tjce.demoiselleshiro.domain.Sistema;
import br.tjce.demoiselleshiro.domain.Usuario;
import br.tjce.demoiselleshiro.persistence.LogsDAO;

@BusinessController
public class LogsBC extends DelegateCrud<Logs, Long, LogsDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public Usuario getUsuario(){
		String usuarioLogin = SecurityUtils.getSubject().getPrincipal().toString();
		return getDelegate().getUserByLogin(usuarioLogin);
	}
	
	@Transactional
	public  void gravarLogPesquisa(String operacao,Sistema sistema, Erro erro){
		Logs log = new Logs();
		log.setData(new Date());
		
		log.setUsuario( (SecurityUtils.getSubject().isAuthenticated())? getUsuario().getUsername()  : "Publico");
		log.setUsertype( (SecurityUtils.getSubject().isAuthenticated())? getUsuario().getPerfis().get(0).getPerfil()  : "Publico");
		log.setOperacao(operacao);
		log.setCodigosistema(sistema.getCodigo());
		log.setDescricaosistema(sistema.getDescricao());
		log.setCodigoerro(erro.getCodigo());
		log.setDescricaoerro(erro.getDescricao());
		insert(log);
	}
	
}
