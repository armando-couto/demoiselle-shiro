package br.tjce.demoiselleshiro.security;

import javax.enterprise.inject.Alternative;

import br.gov.frameworkdemoiselle.internal.configuration.JsfSecurityConfig;
import br.gov.frameworkdemoiselle.internal.configuration.SecurityConfig;
import br.gov.frameworkdemoiselle.internal.implementation.SecurityContextImpl;
import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;
import br.gov.frameworkdemoiselle.util.Beans;

@Alternative
public class Autenticador implements Authenticator {

	private Usuario usuario = null;
	
//	@Inject
//	private Credentials credenciais;

//	@Inject
//	private LoginPerformer performer;

	private static final long serialVersionUID = 1L;

	// SecurityContextImpl

	@Override
	public boolean authenticate() {
		boolean authenticated = false;

////		Usuarios user = performer.tryToLogin(credenciais.getUsername(),
////				credenciais.getSenha());
//
//		if (user != null) {
//			credenciais.clear();
//			this.usuario = new Usuario();
//			this.usuario.setId(user.getUsername());
////			this.usuario.setAttribute("perfil", user.getPerfis().getPerfil());
//			authenticated = true;
//		}
		
		return authenticated;
	}

	@Override
	public User getUser() {
		return this.usuario;
	}

	@Override
	public void unAuthenticate() {
		usuario = null;
	}

}
