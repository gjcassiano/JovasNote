package com.example.jovasnote;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import android.widget.Toast;

public class ServiceConta extends Service {
	

	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();

		
	}
	
	void runbackground(){
		int i = 0;
		while(i < 10){
			i+=1;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			
			}
			Vibrator v =(Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
			v.vibrate(200);
			//Toast.makeText(MainActivity., "Contador: " + i, Toast.LENGTH_SHORT).show();
		}
		this.stopSelf();
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				runbackground();
				
				
			}
		};
		
		Thread t = new Thread(r);
		t.start();
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();

	}

}
