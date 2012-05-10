package com.jayway.xmpplab.model;

import java.io.Serializable;

/**
 * Container class for user credentials.
 */
@SuppressWarnings("serial")
public class UserCredentials implements Serializable {

	private final String userName;
	private final String password;

	public UserCredentials(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
