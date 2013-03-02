package br.tjce.demoiselleshiro.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.util.Beans;

public class MyAuthFilter extends FormAuthenticationFilter {

	private Logger log;
	
	public MyAuthFilter() {
		log = Beans.getReference(Logger.class);
	}
	
	@Override
	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		return super.executeLogin(request, response);
	}
	
	@Override
	protected boolean isLoginRequest(ServletRequest request,
			ServletResponse response) {
		return super.isLoginRequest(request, response);
	}
	
	@Override
	protected boolean isLoginSubmission(ServletRequest request,
			ServletResponse response) {
		return super.isLoginSubmission(request, response);
	}
	
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {				
		if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isInfoEnabled()) {
                    log.info("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isInfoEnabled()) {
                    log.info("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            if (log.isInfoEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }		
	}
	
	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
//		String username = getUsername(request);
//        String password = getPassword(request);
//        log.info(" TRY TO LOGIN WITH " + username);
		return super.createToken(request, response);
	}
	
	
}
