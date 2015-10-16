package com.my.webbrowsert;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
public class handler extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent hand=getIntent();
		Uri url=hand.getData();
		if(url.getScheme().equals("http")||url.getScheme().equals("https")){
			String a=url.toString();
             Intent page=new Intent(handler.this,Page.class);
             page.putExtra("d",a);
             startActivity(page);
		}
	}
	
}
