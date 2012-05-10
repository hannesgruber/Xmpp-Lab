package com.jayway.xmpplab;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jayway.xmpplab.model.Game;
import com.jayway.xmpplab.model.Game.GameListener;
import com.jayway.xmpplab.util.Logg;

public class GameActivity extends Activity implements GameListener{
	
	private XmppLabApplication app;
	private Game game;

	private TextView opponentView;
	private Button a0;
	private Button a1;
	private Button a2;
	private Button b0;
	private Button b1;
	private Button b2;
	private Button c0;
	private Button c1;
	private Button c2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		
		app = (XmppLabApplication) getApplication();
		
		game = app.getGame();
		game.setListener(this);

		opponentView = (TextView) findViewById(R.id.opponent);
		opponentView.setText(game.getOpponent());
		
		a0 = (Button) findViewById(R.id.A0);
		a1 = (Button) findViewById(R.id.A1);
		a2 = (Button) findViewById(R.id.A2);
		b0 = (Button) findViewById(R.id.B0);
		b1 = (Button) findViewById(R.id.B1);
		b2 = (Button) findViewById(R.id.B2);
		c0 = (Button) findViewById(R.id.C0);
		c1 = (Button) findViewById(R.id.C1);
		c2 = (Button) findViewById(R.id.C2);
	}

	public void gameTileClicked(View gameTile){
		
		if(game.isMyTurn() && !game.hasEnded()){
			
			int x = -1;
			int y = -1;
			
			switch (gameTile.getId()) {
			case R.id.A0:
				x = 0;
				y = 2;
				break;
			case R.id.A1:
				x = 1;
				y = 2;
				break;
			case R.id.A2:
				x = 2;
				y = 2;
				break;
			case R.id.B0:
				x = 0;
				y = 1;
				break;
			case R.id.B1:
				x = 1;
				y = 1;
				break;
			case R.id.B2:
				x = 2;
				y = 1;
				break;
			case R.id.C0:
				x = 0;
				y = 0;
				break;
			case R.id.C1:
				x = 1;
				y = 0;
				break;
			case R.id.C2:
				x = 2;
				y = 0;
				break;
			}
			
			if(game.getMarker(x, y).equals("")){
				game.doMove(x, y);
				
				// TODO: Implement sending move
				
			}

		} else if(game.hasEnded()) {
			showToast("Game has ended already...");
		} else {
			showToast("Hold on, it isn´t your turn...");
		}
	}

	private void updateGamePlan() {
		a0.setText(game.getMarker(0,2));
		a1.setText(game.getMarker(1,2));
		a2.setText(game.getMarker(2,2));
		b0.setText(game.getMarker(0,1));
		b1.setText(game.getMarker(1,1));
		b2.setText(game.getMarker(2,1));
		c0.setText(game.getMarker(0,0));
		c1.setText(game.getMarker(1,0));
		c2.setText(game.getMarker(2,0));
	}

	public void onGameMove() {
		runOnUiThread(new Runnable() {
			public void run() {
				updateGamePlan();
			}
		});
	}

	public void onGameWon() {
		Logg.d("onGameWon");
		showToast("YOU WON!!");
	}

	public void onGameLost() {
		Logg.d("onGameLost");
		showToast("YOU LOST!!");
	}

	public void onGameTie() {
		Logg.d("onGameTie");
		showToast("TIE!!");
	}
	
	private void showToast(final String message){
		runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(GameActivity.this, message, Toast.LENGTH_LONG).show();
			}
		});
	}
}
