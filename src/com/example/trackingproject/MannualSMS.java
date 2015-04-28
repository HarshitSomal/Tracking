package com.example.trackingproject;





import java.util.ArrayList;









import android.telephony.SmsManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;


public class MannualSMS extends Activity {
    TextView address;
	EditText noForSms;
	String addr;
	
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mannual_sms);
   
		// =========== fetch Controls =====================
		    noForSms = (EditText)findViewById(R.id.editText1);
			Toast.makeText(this, "Inside Main", Toast.LENGTH_LONG).show();
		    address=(TextView)findViewById(R.id.textView1);
			addr=getIntent().getExtras().getString("hh");
			address.setText(addr);
		 
	}
	 // =========== Send Button for sending sms =====================
	
	public void sendBtn(View v)
	{
		String mobNO = noForSms.getText().toString();
		
		if(mobNO.length() == 0)
		{
			Toast.makeText(this, "Dear User Please Enter the MOb No", Toast.LENGTH_LONG).show();
			
		}
		else
		{
			
			// Send The Sms 
		
			SmsManager sms=SmsManager.getDefault();
			sms.sendTextMessage(mobNO, null, addr, null, null);
			
		}
		
	}
	// =========== Search Button for fetching cell contacts  =====================
	
	public void searchBtn(View v)
	{
		// Open The Contact List
		Toast.makeText(this, "Inside search btn", Toast.LENGTH_LONG).show();
		Intent i = new Intent(MannualSMS.this,Callrecord.class);
		startActivityForResult(i,100);
		
	}
	
	
	// =========== Fixed selected Contact No. from cell Contacts =====================
	
	@Override
	protected void onActivityResult(int requestcode, int resultcode, Intent data) {
		// TODO Auto-generated method stub
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
