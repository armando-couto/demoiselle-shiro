package br.tjce.demoisellecayenne.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MyUtils {

	public static String md5(String password){
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(password.getBytes(), 0, password.length());
			password = new BigInteger(1, m.digest()).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}
	
	public static void addMessage(Severity severity, String message){
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage(severity, message, message));
	}
	
}
