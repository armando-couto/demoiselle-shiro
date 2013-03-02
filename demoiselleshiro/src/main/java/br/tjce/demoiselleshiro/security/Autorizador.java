package br.tjce.demoiselleshiro.security;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.Authorizer;
import br.gov.frameworkdemoiselle.security.SecurityContext;

@Alternative
public class Autorizador implements Authorizer {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	@Inject
	private SecurityContext securityContext;

	@Override
	public boolean hasPermission(String arg0, String arg1) {
		return true;
	}

	@Override
	public boolean hasRole(String arg0) {
		return true;
	}

}
