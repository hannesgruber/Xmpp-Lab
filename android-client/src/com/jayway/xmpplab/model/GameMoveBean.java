package com.jayway.xmpplab.model;


public class GameMoveBean {
	
	public static String EXPECTED_CLASS = "com.jayway.GameMoveBean";
	
	private final int x;
	private final int y;
	
	private final String beanClass = EXPECTED_CLASS;
	
	public GameMoveBean(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}