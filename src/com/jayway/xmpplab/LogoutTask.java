package com.jayway.xmpplab;

import android.content.Intent;
import android.os.AsyncTask;

public class LogoutTask extends AsyncTask<Void, Void, Void> {

	private final MainActivity activity;

	public LogoutTask(MainActivity activity) {
		this.activity = activity;
	}
	
	@Override
	protected void onPreExecute() {
		activity.showDialog(MainActivity.LOGOUT_PROGRESS);
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		Intent xmppIntent = new Intent(activity, XmppService.class);
		xmppIntent.setAction(XmppService.XMPP_ACTION_LOGOUT);
		activity.startService(xmppIntent);
		return null;
		
	}

	@Override
	protected void onPostExecute(Void result) {
		activity.dismissDialog(MainActivity.LOGOUT_PROGRESS);
		
		activity.startActivity(new Intent(activity, LoginActivity.class));
		activity.finish();
	}

}
