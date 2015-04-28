package com.example.trackingproject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;












import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.provider.ContactsContract;

public class Callrecord extends Activity implements OnItemClickListener {
ListView mylist;
MyAdapter adapter;
MyDatabase db;
ArrayList<String[]> contact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_callrecord);
       mylist=(ListView)findViewById(R.id.listView1);
       
		contact= readContacts();   
	     	mylist.setAdapter(new MyAdapter(this,contact));
		    
	        
	        // =============== Handling Event ===========================================================
	        mylist.setOnItemClickListener(this);          	
	}
	public ArrayList<String[]> readContacts()
	{
		//Toast.makeText(this, " Inside read Contacts ", Toast.LENGTH_LONG).show();
		 ContentResolver cr = getContentResolver();
		 
		Cursor cur =  	cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		
		ArrayList <String[]> temp = new ArrayList<String[]>(); 
		while(cur.moveToNext())
		{
			String [] data = new String[2];
			String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
			String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			//Toast.makeText(this, name, Toast.LENGTH_LONG).show();
			data[0] = name;
			 if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
			 {
				// Toast.makeText(this, "inSIDE IF", Toast.LENGTH_LONG).show();

                 // get the phone number
                 Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                                        new String[]{id}, null);
                 while (pCur.moveToNext()) {
                	// Toast.makeText(this, "iNSIDE WHILE", Toast.LENGTH_LONG).show();
                       String phone = pCur.getString(
                              pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                       data[1] = phone;
                       temp.add(data);
                       System.out.println("phone" + phone);
                      // Toast.makeText(this, "phone" + phone, Toast.LENGTH_LONG).show();
                 }
                 pCur.close();
			
			 }
			
		}
		 return temp;
	}
		
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		moveBack(pos);
		
	}
	public void moveBack(int index)
	{
		//==== fetch the record from arraylist on behalf of click
	
		String [] data = contact.get(index);
		Intent i = new Intent();
		i.putExtra("contactdetail",data); // set the record inside intent;
		setResult(RESULT_OK, i);
		finish();// destroy the activity 
		
	}
	class MyAdapter extends BaseAdapter
	{
		ArrayList<String[]> n;
		MyAdapter(Context cntxt,ArrayList<String[]> n)
		{
			this.n = n;
			
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return contact.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View view, ViewGroup arg2) {
			// TODO Auto-generated method stub
			try{
			if(view == null)
			{
				view = getLayoutInflater().inflate(R.layout.items, arg2,false);
			}
			
			String [] data=contact.get(arg0); 
			TextView name = (TextView)view.findViewById(R.id.name);
			name.setText(data[0]);
			TextView mobNo = (TextView)view.findViewById(R.id.mob);
			mobNo.setText(data[1]);
			}catch(Exception e)
			{
				Toast.makeText(Callrecord.this, e.getMessage(), Toast.LENGTH_LONG).show();
				
			}
			return view;
		}
		
	}

}
	
