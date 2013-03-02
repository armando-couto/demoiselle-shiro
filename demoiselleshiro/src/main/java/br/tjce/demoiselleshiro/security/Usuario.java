package br.tjce.demoiselleshiro.security;

import java.util.HashMap;
import java.util.Map;

import br.gov.frameworkdemoiselle.security.User;

public class Usuario implements User {

	private static final long serialVersionUID = 1L;
	
	private String userIdentification;
	
	private Map<Object, Object> attributes = new HashMap<Object, Object>();
	
	@Override
	public Object getAttribute(Object key) {
		return attributes.get(key);			
	}

	@Override
	public String getId() {
		return userIdentification;
	}

	@Override
	public void setAttribute(Object key, Object value) {
		attributes.put(key, value);	
	}
		
	public void setId(String id){
		this.userIdentification = id;
	}

}
