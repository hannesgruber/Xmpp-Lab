package com.jayway.xmpplab;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jayway.xmpplab.model.UserCredentials;
import com.jayway.xmpplab.util.Logg;

/**
 * @author Hannes Gruber, Jayway
 * 
 */
public class LoginActivity extends Activity {

	public static final int LOGIN_PROGRESS = 1;
	
	private TextView result;
	private TextView userName;
	private TextView password;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if(XmppService.isLoggedIn()){
			startActivity(new Intent(this, MainActivity.class));
			finish();
		}
		
		setContentView(R.layout.login);

		result = (TextView) findViewById(R.id.result);
		userName = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		
		SharedPreferences prefs = getSharedPreferences("XmppLabPrefs", Context.MODE_PRIVATE);
		userName.setText(prefs.getString("username", ""));
		password.setText(prefs.getString("password", ""));
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		switch (id) {
		case LOGIN_PROGRESS:
			dialog = new ProgressDialog(this);
			dialog.setCancelable(false);
			((ProgressDialog)dialog).setMessage("Logging in");
			break;
		default:
			dialog = super.onCreateDialog(id);
			break;
		}
		return dialog;
	}

	public void login(View v) {
		
		UserCredentials creds = getUserCredentials();
		
		SharedPreferences prefs = getSharedPreferences("XmppLabPrefs", Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putString("username", creds.getUserName());
		editor.putString("password", creds.getPassword());
		editor.commit();
		
		Logg.d("Logging in");
		displayResult("");
		new LoginTask(this, creds).execute();
	}

	public void displayResult(String s) {
		result.setText(s);
	}
	
	private UserCredentials getUserCredentials(){
		UserCredentials userCredentials = new UserCredentials(userName
				.getText().toString(), password.getText().toString());
		return userCredentials;
	}
}
