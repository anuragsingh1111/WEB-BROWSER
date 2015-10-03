package com.my.webbrowsert;

import java.io.File;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
@SuppressLint({ "SetJavaScriptEnabled", "ClickableViewAccessibility" })

public class handler extends Activity{
	WebView wv;
ProgressBar pb1;
EditText editText1,e2;
Button hb;
ImageButton but2,reload,stop,fwd,ba,tab;
DownloadManager dm;
public String TAG="Webpage";
Bundle extras;
float ad,au,res;
int d=15;
	@SuppressLint("ClickableViewAccessibility") @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainpage);
		super.onCreate(savedInstanceState);
		initcontrols();
		getCacheDir().delete();
	    if(savedInstanceState!=null){
	    	wv.restoreState(savedInstanceState);
	    }
		Intent hand=getIntent();
		Uri url=hand.getData();
		if(url.getScheme().equals("http")){
			String a=url.toString();
			if(a.length()>0){
			LoadUrl(a);
			e2.setText(a);
			editText1.setText(a);
			}
		}
	    reload.setVisibility(ImageButton.INVISIBLE);
		stop.setVisibility(ImageButton.INVISIBLE);
		e2.setVisibility(EditText.INVISIBLE);
		Websettings();
		Listeners();		
}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		wv.saveState(outState);
	}
	
	
	
	@SuppressWarnings("deprecation")
	private void Websettings() {
		// TODO Auto-generated method stub
		wv.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				but2.setVisibility(ImageButton.INVISIBLE);
				hb.setVisibility(ImageButton.VISIBLE);
				reload.setVisibility(ImageButton.VISIBLE);
				e2.setVisibility(EditText.INVISIBLE);
				editText1.setVisibility(EditText.VISIBLE);
				return false;
			}
			
		});
		
   wv.setWebChromeClient(new WebChromeClient(){

	@Override
	public void onProgressChanged(WebView view, int newProgress) {
		// TODO Auto-generated method stub
		pb1.setProgress(newProgress);
		super.onProgressChanged(view, newProgress);
	}
	
	   
   });
   wv.setWebViewClient(new WebViewClient(){

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		// TODO Auto-generated method stub
		pb1.setVisibility(ProgressBar.VISIBLE);
		but2.setVisibility(ImageButton.INVISIBLE);
		reload.setVisibility(ImageButton.INVISIBLE);
		stop.setVisibility(ImageButton.VISIBLE);
		super.onPageStarted(view, url, favicon);
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		// TODO Auto-generated method stub
		pb1.setVisibility(ProgressBar.INVISIBLE);
		but2.setVisibility(ImageButton.INVISIBLE);
		reload.setVisibility(ImageButton.VISIBLE);
		stop.setVisibility(ImageButton.INVISIBLE);
		super.onPageFinished(view, url);
	}

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// TODO Auto-generated method stub
		editText1.setText(url);
		e2.setText(url);
		if(url.endsWith(".exe")||url.endsWith(".mp3")||url.endsWith(".mp4")||url.endsWith(".3gp")){
			Download(url);	
			}
		if(url.startsWith("tel:")){
			Intent i=new Intent(Intent.ACTION_DIAL);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
		return super.shouldOverrideUrlLoading(view, url);
	}
	   
   });
   WebSettings ws=wv.getSettings();
   ws.setDisplayZoomControls(false);
   ws.setJavaScriptEnabled(true);
   ws.setAppCacheEnabled(false);
   ws.setBuiltInZoomControls(true);
   ws.setCacheMode(WebSettings.LOAD_DEFAULT);
   ws.setRenderPriority(RenderPriority.LOW);
   wv.getSettings().setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
   ws.setPluginState(PluginState.ON);
   String page=editText1.getText().toString();
	int l=page.length();
	if(l>0){
		LoadUrl(page);			
	}
		
	}
	private void Listeners() {
		// TODO Auto-generated method stub
		tab.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(handler.this,Page.class);
				String s="https://www.google.com";
				i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
				i.putExtra("d",s);
				startActivity(i);
				
			}
			
		});
	but2.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			editText1.setText("");
			e2.setText("");
			Animation ai=AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.slide_in_left);
			but2.startAnimation(ai);
		}
		
	});
	
	hb.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i=new Intent(getBaseContext(),Web.class);
			startActivity(i);
			
		}
		
	});
	reload.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			wv.reload();
			
		}
		
	});
	stop.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			wv.stopLoading();
			
		}
		
	});
	fwd.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(wv.canGoForward()){
				wv.goForward();
			}
			
		}
		
	});
	ba.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(wv.canGoBack()){
				wv.goBack();
			}
			
		}
		
	});
	
	e2.setOnEditorActionListener(new OnEditorActionListener(){

		@Override
		public boolean onEditorAction(TextView v, int actionId,
				KeyEvent event) {
			// TODO Auto-generated method stub
			if(actionId==EditorInfo.IME_ACTION_GO){
				String local=e2.getText().toString();
				editText1.setText(local);
				LoadUrl(local);
				InputMethodManager im=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				im.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
				e2.setVisibility(EditText.INVISIBLE);
				editText1.setVisibility(EditText.VISIBLE);
				hb.setVisibility(ImageButton.VISIBLE);
			}
			return true;
		}
		
	});
	editText1.setOnTouchListener(new View.OnTouchListener() {
		
		@SuppressLint("ClickableViewAccessibility") @Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			reload.setVisibility(ImageButton.INVISIBLE);
			stop.setVisibility(ImageButton.INVISIBLE);
			hb.setVisibility(ImageButton.INVISIBLE);
			but2.setVisibility(Button.VISIBLE);
			editText1.setVisibility(EditText.INVISIBLE);
			e2.setVisibility(EditText.VISIBLE);
			return false;
		}
	});
e2.setOnTouchListener(new View.OnTouchListener() {
		
		@SuppressLint("ClickableViewAccessibility") @Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			reload.setVisibility(ImageButton.INVISIBLE);
			stop.setVisibility(ImageButton.INVISIBLE);
			hb.setVisibility(ImageButton.INVISIBLE);
			but2.setVisibility(Button.VISIBLE);
			return false;
		}
	});
		
	}
	private void initcontrols() {
		// TODO Auto-generated method stub
		pb1=(ProgressBar)findViewById(R.id.pb1);
		pb1.setMax(100);
		pb1.setProgress(0);
		editText1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText3);
		but2=(ImageButton)findViewById(R.id.but2);
		tab=(ImageButton)findViewById(R.id.tab);
		hb=(Button)findViewById(R.id.hb);
		reload=(ImageButton)findViewById(R.id.reload);
		stop=(ImageButton)findViewById(R.id.stop);
		fwd=(ImageButton)findViewById(R.id.fwd);
		ba=(ImageButton)findViewById(R.id.ba);
	    wv=(WebView)findViewById(R.id.webView2);
		
	}
	private void LoadUrl(String page) {
		// TODO Auto-generated method stub
		if(page.startsWith("http")||page.endsWith(".com")||page.startsWith("www")){
			wv.loadUrl(page);
		}
		else{
			if(page.endsWith(".exe")||page.endsWith(".mp3")||page.endsWith(".mp4")||page.endsWith(".3gp")){
				Download(page);	
			}
			else{
				wv.loadUrl("https://www.google.co.in/search?&q="+page);
			}
		}
		
		
	}
	private void Download(String page) {
		// Download manager
		Uri u=Uri.parse(page);
		File f=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"Webbrowser");
		if(!f.exists()){
			f.mkdir();
		}
		String p=u.toString();
		if((p.endsWith(".exe"))||(p.endsWith(".mp3"))||(p.endsWith(".mp4"))||(p.endsWith(".3gp"))||(p.endsWith(".flac"))||(p.endsWith(".mkv"))||(p.endsWith(".apk"))){
		DownloadManager.Request myrequest=new DownloadManager.Request(u);
		myrequest.allowScanningByMediaScanner();
		myrequest.setVisibleInDownloadsUi(true);
		myrequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        myrequest.setDestinationUri(Uri.fromFile(f));
		DownloadManager dm=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
		dm.enqueue(new Request(u));		
		}
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			if(wv.canGoBack()){
				wv.goBack();
			}
			else{
				Intent i=new Intent(getBaseContext(),Web.class);
				startActivity(i);
				
			}
		}
		return true;
	}
	
	

	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		android.os.Process.killProcess(android.os.Process.myPid());
		super.onDestroy();
		
	}
	
}
