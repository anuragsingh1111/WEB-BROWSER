package com.my.webbrowser;

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
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
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
@SuppressLint("SetJavaScriptEnabled")

public class Page extends Activity{
	WebView wv;
ProgressBar pb1;
EditText editText1;
Button hb;
ImageButton but2,reload,stop,fwd,ba;
DownloadManager dm;
public String TAG="Webpage";
Bundle extras;
float ad,au,res;
int d=15;



	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainpage);
		super.onCreate(savedInstanceState);
		final Animation stopani=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.stop);
		pb1=(ProgressBar)findViewById(R.id.pb1);
		pb1.setMax(100);
		pb1.setProgress(0);
		editText1=(EditText)findViewById(R.id.editText1);
		but2=(ImageButton)findViewById(R.id.but2);
		hb=(Button)findViewById(R.id.hb);
		reload=(ImageButton)findViewById(R.id.reload);
		stop=(ImageButton)findViewById(R.id.stop);
		fwd=(ImageButton)findViewById(R.id.fwd);
		ba=(ImageButton)findViewById(R.id.ba);
	    wv=(WebView)findViewById(R.id.webView2);
	    if(savedInstanceState!=null){
	    	wv.restoreState(savedInstanceState);
	    }
	    reload.setVisibility(ImageButton.INVISIBLE);
		stop.setVisibility(ImageButton.INVISIBLE);
		Bundle extras=getIntent().getExtras();
		String s=extras.getString("d");
		if(((s.length())>0)){
			if(s.startsWith("http")){
				editText1.setText(s);
				wv.loadUrl(s);
			}
			else{
				editText1.setText(s);
				wv.loadUrl("https://www.google.co.in/search?&q="+s);
			}
		}
		
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
		stopani.setRepeatMode(Animation.RESTART);
		stop.startAnimation(stopani);
		super.onPageStarted(view, url, favicon);
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		// TODO Auto-generated method stub
		pb1.setVisibility(ProgressBar.INVISIBLE);
		but2.setVisibility(ImageButton.INVISIBLE);
		reload.setVisibility(ImageButton.VISIBLE);
		stop.setVisibility(ImageButton.INVISIBLE);
		stopani.cancel();
		super.onPageFinished(view, url);
	}

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// TODO Auto-generated method stub
		editText1.setText(url);
		if(url.endsWith(".exe")||url.endsWith(".mp3")||url.endsWith(".mp4")||url.endsWith(".3gp")){
			Download(url);	
			}
		return super.shouldOverrideUrlLoading(view, url);
	}
	   
   });
   WebSettings ws=wv.getSettings();
   ws.setDisplayZoomControls(false);
   ws.setJavaScriptEnabled(true);
   ws.setAppCacheEnabled(true);
   ws.setBuiltInZoomControls(true);
   ws.setCacheMode(WebSettings.LOAD_DEFAULT);
   ws.setRenderPriority(RenderPriority.LOW);
   wv.getSettings().setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
   String appCachePath=getApplicationContext().getCacheDir().getAbsolutePath();
   ws.setAppCachePath(appCachePath);
   ws.setPluginState(PluginState.ON);
   String page=editText1.getText().toString();
	int l=page.length();
	if(l>0){
		LoadUrl(page);			
	}
		
	
	Log.i(TAG, "on resume Invoked");
	but2.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			editText1.setText("");
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
			onDestroy();
			
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
	
	editText1.setOnEditorActionListener(new OnEditorActionListener(){

		@Override
		public boolean onEditorAction(TextView v, int actionId,
				KeyEvent event) {
			// TODO Auto-generated method stub
			if(actionId==EditorInfo.IME_ACTION_GO){
				String local=editText1.getText().toString();
				LoadUrl(local);
				InputMethodManager im=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				im.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
			}
			return true;
		}
		
	});
	editText1.setOnTouchListener(new View.OnTouchListener() {
		
		@SuppressLint("ClickableViewAccessibility") @Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			but2.setVisibility(Button.VISIBLE);
			reload.setVisibility(Button.INVISIBLE);
			stop.setVisibility(Button.INVISIBLE);
			return false;
		}
	});
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
		String name=URLUtil.guessFileName(page,"http","audio/*");
		String p=u.toString();
		if((p.endsWith(".exe"))||(p.endsWith(".mp3"))||(p.endsWith(".mp4"))||(p.endsWith(".3gp"))||(p.endsWith(".flac"))||(p.endsWith(".mkv"))){
		DownloadManager.Request myrequest=new DownloadManager.Request(u);
		myrequest.allowScanningByMediaScanner();
		myrequest.setVisibleInDownloadsUi(true);
		myrequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
		myrequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,File.separator+"WebBrowser"+File.separator+name);
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
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		wv.saveState(outState);
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
