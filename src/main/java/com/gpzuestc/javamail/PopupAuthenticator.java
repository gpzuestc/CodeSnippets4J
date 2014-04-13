package com.gpzuestc.javamail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class PopupAuthenticator extends Authenticator {
	String username = null;
	String password = null;

	public PopupAuthenticator() {
	}

	public PasswordAuthentication performCheck(String user, String pass) {
		this.username = user;
		this.password = pass;
		return getPasswordAuthentication();
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}
