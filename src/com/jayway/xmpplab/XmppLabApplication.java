package com.jayway.xmpplab;

import android.app.Application;

import com.jayway.xmpplab.model.Game;
import com.jayway.xmpplab.model.UserCredentials;

/**
 * 
 * @author Hannes Gruber, Jayway
 *
 */
public class XmppLabApplication extends Application {

	private UserCredentials credentials;
	private Game game;

	public void setGame(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}

	public UserCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(UserCredentials credentials) {
		this.credentials = credentials;
	}
}
