package com.my.webbrowsert;




import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;



public class Web extends Activity {
	EditText edittext2;
	Button button1;
	ImageButton fb,gm,shop,wiki,music,stackflow;
	public String TAG="Anurag";
	RelativeLayout r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        edittext2=(EditText)findViewById(R.id.editText2);
        edittext2.setOnEditorActionListener(new OnEditorActionListener(){

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				// TODO Auto-generated method stub
				if(actionId==EditorInfo.IME_ACTION_SEARCH){
					String s=edittext2.getText().toString();
			        int x=s.length();
			        if(x>0){
					Intent i=new Intent(getBaseContext(),Page.class);
					i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
					i.putExtra("d",s);
					startActivity(i);
					edittext2.setText("");
			        }
				}
				return true;
			}
        	
        });
    	r=(RelativeLayout)findViewById(R.id.mainpage1);
		 button1=(Button)findViewById(R.id.button1);
			button1.setOnClickListener(new OnClickListener(){

				 @Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			        String s=edittext2.getText().toString();
			        int x=s.length();
			        if(x>0){
					Intent i=new Intent(getBaseContext(),Page.class); 
					i.putExtra("d",s);
					i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
					startActivity(i);
					edittext2.setText("");
					
			        }
			        else{
			        	Toast.makeText(getApplicationContext(), "Enter some Text",Toast.LENGTH_SHORT).show();
			        }
				}

			});
			fb=(ImageButton)findViewById(R.id.fb);
			fb.setOnClickListener(new OnClickListener(){

				 @Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					 String s="https://mbasic.facebook.com";   
					Intent i=new Intent(Web.this,Page.class);
					i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
					i.putExtra("d",s);
					startActivity(i);
					
					//we are using this for hiding the keyboard
					
			        	
				}

			});
			gm=(ImageButton)findViewById(R.id.gm);
			gm.setOnClickListener(new OnClickListener(){

				 @Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			        String s="https://gmail.com";   
					Intent i=new Intent(Web.this,Page.class);
					i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
					i.putExtra("d",s);
					startActivity(i);
					
					
			        	
				}

			});
			wiki=(ImageButton)findViewById(R.id.wiki);
			wiki.setOnClickListener(new OnClickListener(){

				 @Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			        String s="https://en.m.wikipedia.org/wiki/Main_Page";   
					Intent i=new Intent(Web.this,Page.class); 
					i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
					i.putExtra("d",s);
					startActivity(i);
					
					
			        	
				}

			});
			shop=(ImageButton)findViewById(R.id.shop);
			shop.setOnClickListener(new OnClickListener(){

				 @Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			        String s="https://m.ebay.in/mobinweb/";   
					Intent i=new Intent(Web.this,Page.class);
					i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
					i.putExtra("d",s);
					startActivity(i);
					
					
			        	
				}

			});
			music=(ImageButton)findViewById(R.id.music);
			music.setOnClickListener(new OnClickListener(){

				 @Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			        String s="https://touch.gaana.com/home.html";   
					Intent i=new Intent(Web.this,Page.class);
					i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
					i.putExtra("d",s);
					startActivity(i);
				
					
			        	
				}

			});
			stackflow=(ImageButton)findViewById(R.id.stackflow);
			stackflow.setOnClickListener(new OnClickListener(){

				 @Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			        String s="https://stackoverflow.com";   
					Intent i=new Intent(Web.this,Page.class);
					i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
					i.putExtra("d",s);
					startActivity(i);
				
					
			        	
				}

			});
         
       
	} 
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			onDestroy();
		}
		return true;
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		android.os.Process.killProcess(android.os.Process.myPid());
		super.onDestroy();
		Log.i(TAG, "on Destroy Invoked");
		
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Log.i(TAG, "on restart Invoked");
		super.onRestart();
	}


	
}
