package br.tjce.demoiselleshiro.view;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;

import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractPageBean;
import br.gov.frameworkdemoiselle.util.Redirector;
import br.tjce.demoiselleshiro.business.UsuariosBC;
import br.tjce.demoiselleshiro.domain.Usuario;
import br.tjce.demoiselleshiro.security.Credentials;

@ViewController
public class LoginMB extends AbstractPageBean{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Credentials credenciais;
	
	@Inject
	private UsuariosBC usuariosBC;
	
	private String userlogin;
	
	private String senha;
		
	public String getErro(){
		HttpServletRequest request = (HttpServletRequest)  FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String fail = (String) request.getAttribute("loginFail");
		if(fail != null){
			return "Usuário e/ou senha invalidos";
		}else{
			return null;
		}
	}
	
	public void sair(){
		SecurityUtils.getSubject().logout();
		Redirector.redirect("/login.jsf");
	}
	
	public String getUser(){
		if(credenciais.getUsername() == null){
			Usuario u = usuariosBC.loadByLogin((String) SecurityUtils.getSubject().getPrincipal());
			credenciais.setUsername(u.getUsername());
			u = null;
		}
		return credenciais.getUsername();
	}	
	
	public boolean isLogged(){
		return SecurityUtils.getSubject().isAuthenticated();
	}

	public void setCredenciais(Credentials credenciais) {
		this.credenciais = credenciais;
	}


	public Credentials getCredenciais() {
		return credenciais;
	}

	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}

	public String getUserlogin() {
		return userlogin;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

}
