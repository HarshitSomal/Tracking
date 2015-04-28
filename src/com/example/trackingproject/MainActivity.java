package com.example.trackingproject;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;
import android.preference.PreferenceManager;

public class MainActivity extends Activity implements OnClickListener   {
EditText mobn,pwdn;
Button logn,cann,signn;
MyDatabase database;
String [] namewithmobileno;
CheckBox checkb;
SharedPreferences prefs;
String mobile,password;


private SharedPreferences loginPreferences;
private SharedPreferences.Editor loginPrefsEditor;
private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     // =========== Fetch Controls =========================
        mobn=(EditText)findViewById(R.id.m);
        pwdn=(EditText)findViewById(R.id.p);
        logn=(Button)findViewById(R.id.log);
        cann=(Button)findViewById(R.id.can);
        signn=(Button)findViewById(R.id.sig);
        checkb=(CheckBox)findViewById(R.id.checkb);   
        logn.setOnClickListener(this);
     //============Initialize the Database=====================
        database=new MyDatabase(this);
     //============Set OnClickListener on Check Button=========
        
        
          loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
		  loginPrefsEditor = loginPreferences.edit();

		  saveLogin = loginPreferences.getBoolean("saveLogin", false);
		  if (saveLogin == true) {
	
		    mobn.setText(loginPreferences.getString("username", ""));
		   
		   pwdn.setText(loginPreferences.getString("password", ""));
		   checkb.setChecked(true);
		  }
	     
        
        
       
        
    }
      //============Set Remember me Button=====================
   
    
    
  //============Forget Password Button===================
    
    public void forgetPassword(View v)
    {
    	
    	Intent i = new Intent(MainActivity.this,RecoverPassword.class);
    	startActivity(i);
    }
  //============Login Button=========
    @Override
    public void onClick(View view)
   {	
    	
	mobile=mobn.getText().toString();
	password=pwdn.getText().toString();
	 if (checkb.isChecked()) {
		    loginPrefsEditor.putBoolean("saveLogin", true);
		    loginPrefsEditor.putString("username", mobile);
		    loginPrefsEditor.putString("password", password);
		    loginPrefsEditor.commit();
		   } else {
		    loginPrefsEditor.clear();
		    loginPrefsEditor.commit();
		   }

		   doSomethingElse();
		  }

    public void doSomethingElse() {
	//System.out.println("mobile no is :: "+mobile + "  password is :: "+password);
	 namewithmobileno=database.getRecord(mobile, password);
	if(namewithmobileno[0]==null)
	{
		Toast.makeText(this,"Invalid",Toast.LENGTH_LONG).show();
	}
	else
	{
		Toast.makeText(this,namewithmobileno[0],Toast.LENGTH_LONG).show();
		Intent p=new Intent(MainActivity.this,Loginpage.class);
		p.putExtra("key",namewithmobileno);
		
		startActivity(p);
	}
    
}
   

public void cancel(View v)
{
finish();
}  
public void signup(View v)
{
Intent i=new Intent(MainActivity.this,Signup.class);
startActivity(i);
}



 
    }



	
   

	


