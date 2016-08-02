package com.example.jovasnote;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MainActivity extends Activity {

	private Vibrator vibrate;
	public List<Conta_info_item> itemList;
	private CustomAdapter customadapter;
	public ContaDAO contadao;

	public void showMsg(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		contadao = new ContaDAO(this);
		vibrate = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);


		itemList = new ArrayList<Conta_info_item>();
		customadapter = new CustomAdapter(this, itemList);

		ListView lv = (ListView) findViewById(R.id.lista_items);

		lv.setAdapter(customadapter);
		
		
		refreshConta();
//		lv.setClickable(true);
//		lv.setFocusable(false);
//		lv.setFocusableInTouchMode(false);
		// startService(new Intent(this, ServiceConta.class));  
		// vibrate.vibrate(200);
		//Intent i = new Intent(this,Conta_info_item.class);
		//this.startActivity(i);
		
   }
		
	

	public void refreshConta() {
		contadao.GetContas();
		ListView lv = (ListView) findViewById(R.id.lista_items);
		lv.setAdapter(customadapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.action_settings) {
			refreshConta();
			return true;
		} else if (id == R.id.action_loguin) {
			login_activity la = new login_activity(this);
			la.Show(1);
			return true;
		} else if (id == R.id.action_add_items) {
			Conta_activity ca = new Conta_activity(this);
			ca.Show(1);
		}

		return super.onOptionsItemSelected(item);
	}


	@Override
	protected void onDestroy() {
		vibrate.vibrate(200);
		vibrate.vibrate(50);
		contadao.close();
		super.onDestroy();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		contadao.close();
		super.onStop();
	}

}
