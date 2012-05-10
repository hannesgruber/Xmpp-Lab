package com.jayway.xmpplab;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;

import com.jayway.xmpplab.model.UserCredentials;
import com.jayway.xmpplab.util.Logg;

public class XmppService extends Service {

	public static final String XMPP_LOGIN_SUCCESS = "xmpp_login_success";
	public static final String XMPP_AUTHENTICATION_ERROR = "xmpp_authentication_error";
	public static final String XMPP_CONNECTION_ERROR = "xmpp_connection_error";
	public static final String XMPP_LOST_CONNECTION = "xmpp_lost_connection";

	public static final String XMPP_ACTION_LOGIN = "xmpp_action_login";
	public static final String XMPP_ACTION_LOGOUT = "xmpp_action_logout";

	private XmppLabApplication app;
	private HandlerThread bgThread;
	private ServiceHandler handler;
	
	private static boolean loggedIn = false;

	private class ServiceHandler extends Handler {

		public ServiceHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(android.os.Message msg) {
			onHandleIntent((Intent) msg.obj);
		}
	}

	@Override
	public void onCreate() {
		Logg.d("XmppService onCreate");
		super.onCreate();
		bgThread = new HandlerThread("XmppService Background Thread");
		bgThread.start();
		handler = new ServiceHandler(bgThread.getLooper());

		app = (XmppLabApplication) getApplication();
	}

	@Override
	public void onDestroy() {
		bgThread.quit();
		bgThread = null;
		handler = null;
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent != null) {
			handler.obtainMessage(0, intent).sendToTarget();
		}
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		throw new UnsupportedOperationException(
				"The Xmpp Service does not support binding");
	}

	/**
	 * handles Intents on a background thread
	 * 
	 * @param intent
	 *            the {@link Intent} to handle
	 */
	private void onHandleIntent(Intent intent) {
		if (XMPP_ACTION_LOGIN.equals(intent.getAction())) {
			UserCredentials userCredentials = (UserCredentials) intent
					.getSerializableExtra("userCredentials");
			
			String result = connect(userCredentials);
			
			sendBroadcast(new Intent(result));
			
		} else if (XMPP_ACTION_LOGOUT.equals(intent.getAction())) {
			
			disconnect();
			
		}
	}

	/**
	 * Connects against the XMPP server using provided credentials. The result
	 * is returned as a String. Possible results are:
	 * 
	 * <ul>
	 * <li>XMPP_LOGIN_SUCCESS</li>
	 * <li>XMPP_AUTHENTICATION_ERROR</li>
	 * <li>XMPP_CONNECTION_ERROR</li>
	 * </ul>
	 * 
	 * @return the result string
	 */
	private String connect(UserCredentials userCredentials) {

		// TODO: Implement
		
		return XMPP_CONNECTION_ERROR;
	}

	private void disconnect() {
		
		// TODO: Implement
		
		stopSelf();
	}

	public static boolean isLoggedIn() {
		return loggedIn;
	}
}
