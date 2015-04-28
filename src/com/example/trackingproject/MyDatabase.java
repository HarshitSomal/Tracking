package com.example.trackingproject;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SlidingDrawer;

public class MyDatabase extends SQLiteOpenHelper 
{
	
	
	MyDatabase(Context cntxt)
	{
		
		super(cntxt, "Signup", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		// Create Table if want to insert row for admin do here
		
		String sql = "create table userData ( userId Integer , Name Text , mobile Text primary key , email Text , password Text )";
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// Drop the table and create new Table
		onCreate(db);
		
	}
	
	public void insertRecord(User u)
	{
		
		// Insert data in table
		try{
		SQLiteDatabase db =  getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put("Name", u.getName());
		cv.put("mobile", u.getMobile());
		cv.put("password",u.getPwd());
		cv.put("email",u.getEmail());
		
		
		
		
		db.insert("userData", null,cv);
		System.out.println("data Inserted");
		
		}
		catch(Exception ex)
		{
			
			System.out.println();
		}
	}
	
	public void updateRecord(User u)
	{
		
		// update data in table
		
		 SQLiteDatabase db = getWritableDatabase();
		
	            // Define the updated row content.
	            ContentValues updatedValues = new ContentValues();
	            // Assign values for each row.
	            updatedValues.put("NAME",u.getName());  
	            updatedValues.put("EMAIL",u.getEmail());
	            updatedValues.put("PASSWORD",u.getPwd());
	          
	 
	            
	            db.update("UserData",updatedValues, null,null);  
	            
	        }       
	
	public void updatepassword(String mobno,String pwd)
	{
		
		// update data in table
		
		 SQLiteDatabase db = getWritableDatabase();
		
	            // Define the updated row content.
	            ContentValues updatedValues = new ContentValues();
	            // Assign values for each row.
	            
	            updatedValues.put("PASSWORD",pwd);
	            String whereclause=" mobile=? ";
	    		String args[]={mobno};
	 
	            
	            db.update("UserData",updatedValues, whereclause,args);  
	            
	        }       
	

	public  String[] getRecord(String mobile,String password)
	{
		
		// Fetch Single record from Database
		
		SQLiteDatabase db = getReadableDatabase();
		
		String [] cols = {"Name","mobile"};
		String whereclause=" mobile=? and password=?";
		String args[]={mobile,password};
		Cursor cr = db.query("userData",cols, whereclause, args, null,null,null);
		String  [] namewithmobileno = new String[2];
		     if(cr.moveToNext())
		  
		    {
		    	 
		    	namewithmobileno[0]= cr.getString(0);
		    	namewithmobileno[1]= cr.getString(1);
					
					
		    	
		    }
		     else
		     {
		    	 
		     }
		   
		     return namewithmobileno;
		    	    
	}
	
	public String[] verifyMobileNo(String mobNo)
	{
		SQLiteDatabase _db = getReadableDatabase();
		String col[] = {"Name","email"};
		String where = "mobile = ?";
		String []selargs = {mobNo};
		
		Cursor cursor = _db.query("userData", col, where, selargs, null, null, null);
		String []name = new String[2];
		if(cursor.moveToNext())
		{
			name[0] = cursor.getString(0);
			name[1] = cursor.getString(1);
			
		}
		else
		{
			name[0] = null;
			name[1] = null;
			
		}
		return name ;
	}
public String[] getAllRecordForList(String mobile)
{
	
	//  Fetch All record from Database
	SQLiteDatabase db =  getReadableDatabase();
	String tableName = "userData";
	String colName[] = {"Name","email","password"};
	String ss="mobile=?";
	String args[]={mobile};
	Cursor cr = db.query(tableName, colName, ss,args, null, null, null);
	String[] data=new String[3];
	 if(cr.moveToNext())
		  
	    {
	    	 
	    	data[0]= cr.getString(0);
	    	data[1]= cr.getString(1);
	    	data[2]= cr.getString(2);	
				
	    	
	    }
	     else
	     {
	    	 
	     }
	   
	     return data;
	    	    
}


}