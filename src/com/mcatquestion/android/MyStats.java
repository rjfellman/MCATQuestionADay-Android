package com.mcatquestion.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class MyStats extends Activity{
	
	WebView physicsPCT;
	WebView chemPCT;
	WebView bioPCT;
	WebView ochemPCT;
	WebView barcharts;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mystats);
        
        barcharts = (WebView) findViewById(R.id.barchartsWebView);
        physicsPCT = (WebView) findViewById(R.id.physicsMarkerWebView);
        chemPCT = (WebView) findViewById(R.id.chemistryMarkerWebView);
        bioPCT = (WebView) findViewById(R.id.bioMarkerWebView);
        ochemPCT = (WebView) findViewById(R.id.ochemMarkerWebView);
        
        barcharts.getSettings().setJavaScriptEnabled(true);
        barcharts.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Bargraphs-Stats.html");
        barcharts.setBackgroundColor(0x00000000);
        
        physicsPCT.getSettings().setJavaScriptEnabled(true);
        physicsPCT.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Percentiles.html");
        physicsPCT.setBackgroundColor(0x00000000);
        
        chemPCT.getSettings().setJavaScriptEnabled(true);
        chemPCT.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Percentiles.html");
        chemPCT.setBackgroundColor(0x00000000);
        
        bioPCT.getSettings().setJavaScriptEnabled(true);
        bioPCT.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Percentiles.html");
        bioPCT.setBackgroundColor(0x00000000);
        
        ochemPCT.getSettings().setJavaScriptEnabled(true);
        ochemPCT.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Percentiles.html");
        ochemPCT.setBackgroundColor(0x00000000);
        
    }
}
