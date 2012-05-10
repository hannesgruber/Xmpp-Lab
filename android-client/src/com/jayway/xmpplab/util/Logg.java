package com.jayway.xmpplab.util;

import android.util.Log;

public class Logg {

	private static final String TAG = "XmppLab";
	private static boolean enabled = true;

	public static void enable() {
		enabled = true;
	}

	public static void disable() {
		enabled = false;
	}

	public static void v(String msg) {
		if (enabled)
			Log.v(TAG, msg);
	}

	public static void v(String msg, Throwable t) {
		if (enabled)
			Log.v(TAG, msg, t);
	}

	public static void i(String msg) {
		if (enabled)
			Log.i(TAG, msg);
	}

	public static void i(String msg, Throwable t) {
		if (enabled)
			Log.i(TAG, msg, t);
	}

	public static void d(String msg) {
		if (enabled)
			Log.d(TAG, msg);
	}

	public static void d(String msg, Throwable t) {
		if (enabled)
			Log.d(TAG, msg, t);
	}

	public static void w(String msg) {
		if (enabled)
			Log.w(TAG, msg);
	}

	public static void w(String msg, Throwable t) {
		if (enabled)
			Log.w(TAG, msg, t);
	}

	public static void e(String msg) {
		if (enabled)
			Log.e(TAG, msg);
	}

	public static void e(String msg, Throwable t) {
		if (enabled)
			Log.e(TAG, msg, t);
	}
}
