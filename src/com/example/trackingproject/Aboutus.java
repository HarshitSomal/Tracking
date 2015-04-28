package com.example.trackingproject;



import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Aboutus extends Activity{
	
	MediaPlayer mp;
	
	@Override
     	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.aboutus);
	    
	//------------Background Music--------------
	   
	    mp = MediaPlayer.create(this, R.raw.backgroundmusic);
        mp.start();

	//-----------Sleep Thread for 8s------------------
	
		Thread timer = new Thread(){
			public void run(){
				try{
					TimeUnit.SECONDS.sleep(15);					
				} catch (InterruptedException e){
					e.printStackTrace();
				}
				System.exit(0);
			}
		
		};
		timer.start();
	}

}
