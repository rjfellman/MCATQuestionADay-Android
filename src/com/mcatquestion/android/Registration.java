package com.mcatquestion.android;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.*;

public class Registration extends Activity{
	
	WebView regView;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        
        final ProgressBar progBarRegistration;
        progBarRegistration = (ProgressBar) findViewById(R.id.progressBar2);
        
        regView = (WebView) findViewById(R.id.webview_reg);
        regView.getSettings().setJavaScriptEnabled(true);
        regView.loadUrl("http://www.mcatquestionaday.com/registration_mobile.php");
        regView.setBackgroundColor(0x00000000);
        
        regView.setWebViewClient(new WebViewClient() {  
			public void onPageFinished(WebView view, String url)  
			{  
				progBarRegistration.setVisibility(ProgressBar.GONE);
			}  
			
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
			{
				
			}
		});
        
    }
}
