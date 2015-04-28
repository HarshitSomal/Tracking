package com.example.trackingproject;






import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class Signup extends Activity {
EditText name,mobilee,email,pwd,cpwd;
MyDatabase database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		    name=(EditText)findViewById(R.id.etxtmobno);
	        mobilee=(EditText)findViewById(R.id.add);    
		    email=(EditText)findViewById(R.id.editText3);
	        pwd=(EditText)findViewById(R.id.editText4);
	        cpwd=(EditText)findViewById(R.id.editText5);
	       
	     // ============= Intilize the variables =================
			database = new MyDatabase(this);
			database.getWritableDatabase();
	}
public void submit(View v)
{    
	User u = new User();
	u.setName(name.getText().toString());
	u.setMobile(mobilee.getText().toString());
	u.setEmail(email.getText().toString());
	u.setPwd(pwd.getText().toString());
	u.setCpwd(cpwd.getText().toString());
	
	if(u.getPwd().equals(u.getCpwd()))
	{
		database.insertRecord(u);
	}
	else
	{
		Toast.makeText(this, "Pwd and Confirm pwd not match", Toast.LENGTH_LONG).show();
		
	}
	name.setText("");
	pwd.setText("");
	cpwd.setText("");
	email.setText("");
	mobilee.setText("");
	
}


public void cancell(View v)
{
	finish();
	
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup, menu);
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
