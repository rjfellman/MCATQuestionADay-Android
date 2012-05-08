package com.mcatquestion.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.view.MotionEvent;

public class MyStats extends Activity{
	
	WebView physicsPCT;
	WebView chemPCT;
	WebView bioPCT;
	WebView ochemPCT;
	WebView physicsScore,chemistryScore,biologyScore,orgoScore;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mystats);
        
        final AppPreferences preferences = new AppPreferences(this);
        
        System.out.println(preferences.getUsername());
        Log.i("Test",preferences.getUsername());
        
        physicsScore = (WebView) findViewById(R.id.physicsScore);
        biologyScore = (WebView) findViewById(R.id.biologyScore);
        chemistryScore = (WebView) findViewById(R.id.chemistryScore);
        orgoScore = (WebView) findViewById(R.id.orgoScore);
            
        physicsPCT = (WebView) findViewById(R.id.physicsMarkerWebView);
        chemPCT = (WebView) findViewById(R.id.chemistryMarkerWebView);
        bioPCT = (WebView) findViewById(R.id.bioMarkerWebView);
        ochemPCT = (WebView) findViewById(R.id.ochemMarkerWebView);
        
        physicsScore.getSettings().setJavaScriptEnabled(true);
        physicsScore.setScrollContainer(false);
        physicsScore.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Bargraphs-Stats.php?userid="+preferences.getUsername()+"&subject=physics");
        physicsScore.setBackgroundColor(0x00000000);
        
        physicsScore.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {//
                return true;
            }
        });
        
        biologyScore.getSettings().setJavaScriptEnabled(true);
        biologyScore.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Bargraphs-Stats.php?userid="+preferences.getUsername()+"&subject=bio");
        biologyScore.setBackgroundColor(0x00000000);
        
        biologyScore.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        
        chemistryScore.getSettings().setJavaScriptEnabled(true);
        chemistryScore.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Bargraphs-Stats.php?userid="+preferences.getUsername()+"&subject=chem");
        chemistryScore.setBackgroundColor(0x00000000);
        
        chemistryScore.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        
        orgoScore.getSettings().setJavaScriptEnabled(true);
        orgoScore.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Bargraphs-Stats.php?userid="+preferences.getUsername()+"&subject=ochem");
        orgoScore.setBackgroundColor(0x00000000);
        
        orgoScore.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        
        physicsPCT.getSettings().setJavaScriptEnabled(true);
        physicsPCT.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Percentiles.php?userid="+preferences.getUsername()+"&subject=physics");
        physicsPCT.setBackgroundColor(0x00000000);
        
        chemPCT.getSettings().setJavaScriptEnabled(true);
        chemPCT.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Percentiles.php?userid="+preferences.getUsername()+"&subject=chem");
        chemPCT.setBackgroundColor(0x00000000);
        
        bioPCT.getSettings().setJavaScriptEnabled(true);
        bioPCT.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Percentiles.php?userid="+preferences.getUsername()+"&subject=bio");
        bioPCT.setBackgroundColor(0x00000000);
        
        ochemPCT.getSettings().setJavaScriptEnabled(true);
        ochemPCT.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Percentiles.php?userid="+preferences.getUsername()+"&subject=ochem");
        ochemPCT.setBackgroundColor(0x00000000);
        
    }
}
