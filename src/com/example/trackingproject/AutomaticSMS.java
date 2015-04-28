package com.example.trackingproject;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import android.telephony.SmsManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class AutomaticSMS extends Activity  {
	
EditText noForSms;
TextView address;
TextView date;
String addr;
Handler mHandler;
TextView textCounter;
Button send,cancel;

	        @Override
	        protected void onCreate(Bundle savedInstanceState)
	       {
		       super.onCreate(savedInstanceState);
		       setContentView(R.layout.activity_automatic_sms);
		       
		   // =========== fetch Controls =====================
		         date = (TextView)findViewById(R.id.datew);
		         noForSms = (EditText)findViewById(R.id.editText1);
				 send=(Button)findViewById(R.id.send);	
				 cancel=(Button)findViewById(R.id.time);
				 address=(TextView)findViewById(R.id.textView1);
				 addr=getIntent().getExtras().getString("hh");
				 address.setText(addr);
	       }
	        
	        public void sendBtn(View v)
	        
	        //to schedule messages and runnables to be executed as some point in the future using handler class
	        {
	            mHandler = new Handler();
	            mHandler.postDelayed(mRunnable, 0);
	          }

	          private Runnable mRunnable = new Runnable() {

	            @Override
	            public void run() {
	            	//Fixed the mobile no. of sender
	            	final String mobNO = noForSms.getText().toString();
	            	//Set & display Calender with time format
		        	Calendar calendar = Calendar.getInstance();
				    SimpleDateFormat simpleDateFormat = 
			        new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
				    final String strDate = simpleDateFormat.format(calendar.getTime());
	               //Schedule it again
				   
				    
				   mHandler.postDelayed(mRunnable,3600000);
				   
	               date.setText(strDate);
				    //Send SMS by SMS Manager Class 
				   SmsManager sms=SmsManager.getDefault();
				   sms.sendTextMessage(mobNO, null, addr, null, null);
	              
	            }
	          };
	        	//Remove pending execution from Handler
	        
	        public void stop(View v)
	        {
	        	if(mHandler!=null)
	        	{
	        		mHandler.removeCallbacks(mRunnable);
	        	}
	        }
	        
	       
			  
		
			
			// =========== Search Button for fetching cell contacts  =====================
			
			public void searchBtn(View v)
			{
				// Open The Contact List
				Toast.makeText(this, "Inside search btn", Toast.LENGTH_LONG).show();
				Intent i = new Intent(AutomaticSMS.this,Callrecord.class);
				startActivityForResult(i,100);
				
			}
			
			// =========== Fixed selected Contact No. from cell Contacts =====================
			      @Override
			      protected void onActivityResult(int requestcode, int resultcode, Intent data) 
			      {
				   super.onActivityResult(requestcode, resultcode, data);
				    if(requestcode == 100)
			     	{
					if(resultcode == RESULT_OK)
					{
						
						String [] user = data.getStringArrayExtra("contactdetail");
						Toast.makeText(this, "Send sms to :: "+user[0]+" "+user[1], Toast.LENGTH_LONG).show();
						noForSms.setText(user[1]);
					}
				}
			}

				
		}


	