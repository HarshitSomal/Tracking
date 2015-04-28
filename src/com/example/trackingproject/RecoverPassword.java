package com.example.trackingproject;

import java.util.Random;


import android.telephony.SmsManager;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class RecoverPassword extends Activity {

	EditText _mobNo;
	MyDatabase _db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recover_password);
		

		// ============ fetch Controls ============
		_mobNo = (EditText)findViewById(R.id.etxtmobno);
				
		// ============ Intilizing the Objects ============
		_db = new MyDatabase(this);
	}

	
	public void doneBtn(View V)
	{
		String mobNo = _mobNo.getText().toString();
		String name[] = _db.verifyMobileNo(mobNo);
		Random rm = new Random();
		int newNo= rm.nextInt(20);
		String newpwd = name[0]+newNo;
		if(name[0] == null)
		{
			Toast.makeText(this,"Sorry Dear Your Mobile No is Invalid",Toast.LENGTH_LONG).show();
			
		}
		else
		{
			Toast.makeText(this,name[1]+"Pwd Send to ur No and email ",Toast.LENGTH_LONG).show();
			// Use Intent To Send Data  Via Email ..
			
		Intent i=new Intent(Intent.ACTION_SEND);
		i.setData(Uri.parse("mailto:"));
		i.setType("Text/plain");
		i.putExtra(Intent.EXTRA_EMAIL, name[1]);
		i.putExtra(Intent.EXTRA_SUBJECT, "Dear user Ur New Pwd Is::");
		i.putExtra(Intent.EXTRA_TEXT,newpwd);
		startActivity(i);
		
		
    	SmsManager sms=SmsManager.getDefault();
		sms.sendTextMessage(mobNo, null, newpwd, null,null);
	
		_db.updatepassword(mobNo, newpwd);
		Toast.makeText(this,"New Pwd is updated is database", Toast.LENGTH_LONG).show();	
			
		}
	}
	

	
}
