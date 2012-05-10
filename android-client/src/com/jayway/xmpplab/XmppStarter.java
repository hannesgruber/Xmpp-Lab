package com.jayway.xmpplab;

import java.util.concurrent.Semaphore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jayway.xmpplab.model.UserCredentials;

/**
 * XmppStarter is a helper class to start the XmppService synchronously.
 */
public class XmppStarter {

	private final Semaphore xmppLock = new Semaphore(0);
	private final LoginReceiver receiver = new LoginReceiver();
	private String xmppResult;

	/**
	 * starts the XmppService and waits until it signals that it is fully started.
	 * 
	 * @param context
	 *            the {@link Context} used to start the service.
	 * @param userCredentials
	 *            the {@link UserCredentials} used by the XmppService to log on.
	 * @return one of {@link XmppService#XMPP_LOGIN_SUCCESS},
	 *         {@link XmppService#XMPP_AUTHENTICATION_ERROR} or
	 *         {@link XmppService#XMPP_CONNECTION_ERROR}
	 */
	public String startXmppService(Context context, com.jayway.xmpplab.model.UserCredentials userCredentials) {
		xmppLock.drainPermits();
		context.registerReceiver(receiver, getIntentFilter());

		Intent xmppIntent = new Intent(context, XmppService.class);
		xmppIntent.setAction(XmppService.XMPP_ACTION_LOGIN);
		xmppIntent.putExtra("userCredentials", userCredentials);
		context.startService(xmppIntent);

		waitForLoginBroadcast();
		context.unregisterReceiver(receiver);
		return xmppResult;
	}

	private void waitForLoginBroadcast() {
		try {
			xmppLock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private IntentFilter getIntentFilter() {
		IntentFilter filter = new IntentFilter();
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		filter.addAction(XmppService.XMPP_LOGIN_SUCCESS);
		filter.addAction(XmppService.XMPP_AUTHENTICATION_ERROR);
		filter.addAction(XmppService.XMPP_CONNECTION_ERROR);
		return filter;
	}

	/**
	 * Receives XMPP login result and releases lock to allow the
	 * {@link XmppStarter#startXmppService(Context, UserCredentials)} method to complete.
	 */
	private class LoginReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			xmppResult = intent.getAction();
			xmppLock.release();
		}

	}

}
