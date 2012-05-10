package com.jayway.xmpplab.model;

public class Game {
	
	private final String[][] gamePlan;
	private boolean myTurn;
	private final String myMarker;
	private final String opponentMarker;
	private GameListener listener;
	private boolean hasEnded;
	private String opponent;
	
	public Game(boolean myTurn, String myMarker, String opponentMarker){
		gamePlan = new String[3][3];
		
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				gamePlan[y][x] = "";
			}
		}
		
		this.myTurn = myTurn;
		this.myMarker = myMarker;
		this.opponentMarker = opponentMarker;
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public String getMyMarker() {
		return myMarker;
	}

	public String getOpponentMarker() {
		return opponentMarker;
	}
	
	public void doMove(int x, int y){

		if(gamePlan[y][x].isEmpty()){
			
			String marker = myTurn ? myMarker : opponentMarker;
			gamePlan[y][x] = marker;
			
			myTurn = !myTurn;
			
			if(listener != null){
				listener.onGameMove();
			}
			
			checkWin();
		}
	}
	
	private void checkWin() {
		
		// check vertical
		for (int x = 0; x < 3; x++) {
			if(gamePlan[0][x].equals(gamePlan[1][x]) && gamePlan[1][x].equals(gamePlan[2][x])){
				if(gamePlan[0][x].equals(myMarker)){
					win();
					return;
				} else if(gamePlan[0][x].equals(opponentMarker)){
					loose();
					return;
				}
			}
		}
		
		// check horizontal
		for (int y = 0; y < 3; y++) {
			if(gamePlan[y][0].equals(gamePlan[y][1]) && gamePlan[y][1].equals(gamePlan[y][2])){
				if(gamePlan[y][0].equals(myMarker)){
					win();
					return;
				} else if(gamePlan[y][0].equals(opponentMarker)){
					loose();
					return;
				}
			}
		}
		
		// check diagonal
		if(gamePlan[0][0].equals(gamePlan[1][1]) && gamePlan[1][1].equals(gamePlan[2][2])){
			if(gamePlan[0][0].equals(myMarker)){
				win();
				return;
			} else if(gamePlan[0][0].equals(opponentMarker)){
				loose();
				return;
			}
		}
		if(gamePlan[0][2].equals(gamePlan[1][1]) && gamePlan[1][1].equals(gamePlan[2][0])){
			if(gamePlan[0][2].equals(myMarker)){
				win();
				return;
			} else if(gamePlan[0][2].equals(opponentMarker)){
				loose();
				return;
			}
		}
		
		// Check for tie
		boolean tie = true;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if(gamePlan[y][x].isEmpty()){
					tie = false;
				}
			}
		}
		if(tie){
			tie();
		}
	}
	
	private void win(){
		listener.onGameWon();
		hasEnded = true;
	}
	private void loose(){
		listener.onGameLost();
		hasEnded = true;
	}
	private void tie(){
		listener.onGameTie();
		hasEnded = true;
	}
	
	public void setListener(GameListener listener){
		this.listener = listener;
	}
	
	public interface GameListener{
		
		public void onGameMove();
		
		public void onGameWon();
		
		public void onGameLost();
		
		public void onGameTie();
		
	}

	public String getMarker(int x, int y) {
		return gamePlan[y][x];
	}

	public boolean hasEnded() {
		return hasEnded;
	}

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}
}
