package com.example.trackingproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class Loginpage extends Activity implements LocationListener {
TextView username,latlong;
Button settings;
String[] name;
String strCompleteAddress;

	GoogleMap map;
	@SuppressLint("NewApi")
	@Override
	    protected void onCreate(Bundle savedInstanceState)
	  {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginpage);
		
		//----------Fetch IDs---------------
		username=(TextView)findViewById(R.id.usern);
		latlong=(TextView)findViewById(R.id.textView1);
		settings=(Button)findViewById(R.id.set);
		
		//---------Set Username-------------
		
		name=getIntent().getExtras().getStringArray("key");
		username.setText(name[0]);
		
		
		registerForContextMenu(settings);
		
		 // Getting reference to the MapFragment of activity_main.xml
		MapFragment fm = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
		
		// Getting GoogleMap object from the fragment
		map = fm.getMap();
		
		// Set Map type.......
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		// Enabling MyLocation Layer of Google Map
		map.setMyLocationEnabled(true);				
				
		
		 // Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);

        if(location!=null){
                onLocationChanged(location);
        }

        locationManager.requestLocationUpdates(provider, 20000, 0, this);
    }
	



@Override
public void onLocationChanged(Location location) {
	
	
	
	// Getting latitude of the current location
	double latitude = location.getLatitude();
	
	// Getting longitude of the current location
	double longitude = location.getLongitude();		
	
	// Creating a LatLng object for the current location
	LatLng latLng = new LatLng(latitude, longitude);
	
	// Showing the current location in Google Map
	map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
	
	// Zoom in the Google Map
	map.animateCamera(CameraUpdateFactory.zoomTo(15));		
	
	
	// Setting latitude and longitude in the TextView latlong
	latlong.setText("Latitude:" +  latitude  + ", Longitude:"+ longitude );	
	
	// Created an object of the Geocoder class	
	Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
	 try {
     //Called the getFromLocation method, provided it with the latitude and longitude values and the number of search results that will be returned.
	 List<Address> addresses = geoCoder.getFromLocation(latitude,longitude, 2);
	 
	 strCompleteAddress= "";
	 if (addresses.size() > 0)
	 {
		 
    //After this we concatenate each value in the Addresses array list to get a single string with the complete location address.
	 for (int i=0; i<addresses.get(0).getMaxAddressLineIndex();i++)
	strCompleteAddress+= addresses.get(0).getAddressLine(i)+ "\n";
	 }


	 }
	 catch (IOException e) {
		 
		 e.printStackTrace();
		 }
	 
	 
}




@Override
public void onProviderDisabled(String arg0) {
	// TODO Auto-generated method stub
	
}




@Override
public void onProviderEnabled(String arg0) {
	// TODO Auto-generated method stub
	
}




@Override
public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
	// TODO Auto-generated method stub
	
}

//------------Context Menu on setting button--------
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) 
	{
		
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater i=getMenuInflater();
	    i.inflate(R.menu.loginpage, menu);
	          
	}	
	
	//-----------On Event Handle on selected Item of Context Menu-----------------
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		
		switch(item.getItemId())
		{
	//----------Update Data----------------
		
		case R.id.up:
			
			Toast.makeText(this, "Update data", Toast.LENGTH_LONG).show();
			
			Intent i=new Intent(Loginpage.this,Updateform.class);
			i.putExtra("data", name);
			startActivity(i);
			break;
	//----------Manual SMS-----------------
			
		case R.id.msms:
			
			Toast.makeText(this, "Manual sms", Toast.LENGTH_LONG).show();
			Intent a=new Intent(Loginpage.this,MannualSMS.class);
			a.putExtra("hh",strCompleteAddress);
			startActivity(a);
			break;
			
	 //----------Automatic SMS----------------	
			
		case R.id.auto:
			Intent b=new Intent(Loginpage.this,AutomaticSMS.class);
			b.putExtra("hh",strCompleteAddress);
			startActivity(b);
			Toast.makeText(this, "Automatic sms", Toast.LENGTH_LONG).show();
			break;
			
	  //----------About Us----------------------
			
		case R.id.About:
	
			Toast.makeText(this, "About us	", Toast.LENGTH_LONG).show();
			
			Intent p=new Intent(Loginpage.this,Aboutus.class);
			startActivity(p);
		       
			break;
		//----------Logout Dialog Box----------------
			
			case R.id.log:
				AlertDialog.Builder dialogbuilder=new AlertDialog.Builder(this);
				dialogbuilder.setTitle("Login");
				dialogbuilder.setMessage("Do you want to logout........");
				dialogbuilder.setCancelable(false);
				
				dialogbuilder.setPositiveButton("Yes",new OnClickListener()
				{
					
			@Override
     		public void onClick(DialogInterface arg0, int arg1) 
			        {
						
						Intent i=new Intent(Loginpage.this,MainActivity.class);
						startActivity(i);
						finish();
					}
				});
				
				dialogbuilder.setNegativeButton("No",new OnClickListener()
				{

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
						
					}
					
				});
				dialogbuilder.create().show();
				
				break;
				
		}
		return false;
	}
				
}
