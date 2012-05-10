package com.jayway.xmpplab;

import android.content.Intent;
import android.os.AsyncTask;

import com.jayway.xmpplab.model.UserCredentials;
import com.jayway.xmpplab.util.Logg;

public class LoginTask extends AsyncTask<Void, Void, String> {

	private final LoginActivity activity;
	private final UserCredentials userCredentials;

	public LoginTask(LoginActivity activity, UserCredentials userCredentials) {
		this.activity = activity;
		this.userCredentials = userCredentials;
	}
	
	@Override
	protected void onPreExecute() {
		activity.showDialog(LoginActivity.LOGIN_PROGRESS);
	}
	
	@Override
	protected String doInBackground(Void... params) {
		Logg.d("LoginTast starting XmppService");
		return new XmppStarter().startXmppService(activity, userCredentials);
	}

	@Override
	protected void onPostExecute(String result) {
		Logg.d("LoginTast result:" + result);
		activity.dismissDialog(LoginActivity.LOGIN_PROGRESS);
		activity.displayResult(result);
		
		if(result.equals(XmppService.XMPP_LOGIN_SUCCESS)){
			((XmppLabApplication)activity.getApplication()).setCredentials(userCredentials);
			activity.startActivity(new Intent(activity, MainActivity.class));
			activity.finish();
		}
	}

}
