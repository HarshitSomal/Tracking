package com.example.trackingproject;


import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class Splashscreen extends Activity {
	MediaPlayer song;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);
       
		song = MediaPlayer.create(Splashscreen.this, R.raw.welcome);
		
		song.start();
		
		
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Thread splashThread=new Thread()
		{
			public void run()
			{
				for(int i=0;i<2;i++)
				{
					try{
						 TimeUnit.SECONDS.sleep(3);
				}
					catch(InterruptedException ex)
					{
						System.out.println(ex.getMessage());
					}
			}
			Intent k=new Intent(Splashscreen.this,MainActivity.class);
			startActivity(k);
			finish();
			
		}
		

		
	};
	splashThread.start();
	}






	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splashscreen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	

}
