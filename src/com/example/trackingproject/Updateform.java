package com.example.trackingproject;




import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class Updateform extends Activity {
String[] mob;

MyDatabase db;
EditText name,mobilee,email,pwd,cpwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updateform);

	   
		
		name=(EditText)findViewById(R.id.etxtmobno);
        mobilee=(EditText)findViewById(R.id.add);    
	    email=(EditText)findViewById(R.id.editText3);
        pwd=(EditText)findViewById(R.id.editText4);
        cpwd=(EditText)findViewById(R.id.editText5);
        
        mob=getIntent().getExtras().getStringArray("data");
		mobilee.setText(mob[1]);
        
       mobilee.setEnabled(false);
        db=new MyDatabase(this);
		db.getWritableDatabase();	
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
     
     String m = mobilee.getText().toString();

	 String[] up=db.getAllRecordForList(m);
	
	Toast.makeText(this,up[0],Toast.LENGTH_LONG).show();
	name.setText(up[0]);
	email.setText(up[1]);
	pwd.setText(up[2]);
    cpwd.setText(up[2]);
	}

public void update(View v)
{
	 
		User u = new User();
		u.setName(name.getText().toString());
		u.setEmail(email.getText().toString());
		u.setPwd(pwd.getText().toString());
		
		
		
     	db.updateRecord(u);
		
     	name.setText("");
    	email.setText("");
    	pwd.setText("");
    	cpwd.setText("");
}
public void cancel(View v)
{
	finish();
}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.updateform, menu);
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
