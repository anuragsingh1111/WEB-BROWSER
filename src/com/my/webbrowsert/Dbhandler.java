package com.my.webbrowsert;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhandler extends SQLiteOpenHelper{
	static String DATABASE_NAME="Webhistory";
	static int DATABASE_VERSION=1;
	static String DATABASE_TABLE="Browser";
	static String DATABASE_COLUMN_HISTORY="History";
	static String DATABASE_COLUMN_DATE="Date";
	static int ID;

	public Dbhandler(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String query=
				"CREATE TABLE"+DATABASE_TABLE+"("+ID+"INTEGER PRIMARY KEY,"+DATABASE_COLUMN_HISTORY+"TEXT"+")";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//on upgrade
		db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE);
		onCreate(db);
		
	}
	//adding a history to db
	public void addHistory(History h){
		ContentValues cv=new ContentValues();
		cv.put(DATABASE_COLUMN_HISTORY,h.get_webhistory());
		SQLiteDatabase db=getWritableDatabase();
		db.insert(DATABASE_TABLE, null,cv);
		db.close();
	}
	//For deleting the product
	public void deleteHistory(String history){
		SQLiteDatabase db=getWritableDatabase();
		db.execSQL("DELETE FROM"+DATABASE_TABLE+"WHERE"+DATABASE_COLUMN_HISTORY+"=/*"+history+"/*;");
		
		db.close();
	}
	public List<History> getbHistory(){
		List<History> bhistory=new ArrayList<History>();
		String query="SELECT *FROM"+DATABASE_TABLE;
		SQLiteDatabase db=getWritableDatabase();
		Cursor c=db.rawQuery(query, null);
		if(c.moveToFirst()){
			do{History h=new History();
			h.set_id(Integer.parseInt(c.getString(0)));
			h.set_webhistory(c.getString(1));
			bhistory.add(h);
			}while(c.moveToNext());
		}
		return bhistory;
		
	}

}
