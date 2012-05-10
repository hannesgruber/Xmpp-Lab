package com.jayway.xmpplab;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author Hannes Gruber, Jayway
 * 
 */
public class MainActivity extends Activity {

	public static final int LOGOUT_PROGRESS = 1;
	
	private XmppLabApplication app;

	private ListView list;
	private ArrayList<String> listItems;
	private ArrayAdapter<String> listAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		app = (XmppLabApplication) getApplication();
		
		list = (ListView) findViewById(R.id.list);
		listItems = new ArrayList<String>();
		listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
		list.setAdapter(listAdapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> list, View itemView, int index, long id) {
				
				// TODO: Implement
				
			}
		});
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		switch (id) {
		case LOGOUT_PROGRESS:
			dialog = new ProgressDialog(this);
			dialog.setCancelable(false);
			((ProgressDialog)dialog).setMessage("Logging out");
			break;
		default:
			dialog = super.onCreateDialog(id);
			break;
		}
		return dialog;
	}
	
	public void logout(View view){
		new LogoutTask(this).execute();
	}
}
