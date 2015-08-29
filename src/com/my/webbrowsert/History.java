package com.my.webbrowsert;

public class History {
	int _id;
	String _webhistory;
	String _date;
	public History(){
		
	}
	public History(String webhistory){
		this._webhistory=webhistory;
		
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String get_webhistory() {
		return _webhistory;
	}
	public void set_webhistory(String _webhistory) {
		this._webhistory = _webhistory;
	}
	public String get_date() {
		return _date;
	}
	public void set_date(String _date) {
		this._date = _date;
	}
	
	}
