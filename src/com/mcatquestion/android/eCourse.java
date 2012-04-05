package com.mcatquestion.android;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class eCourse extends Activity{
	
	WebView ecourseView;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecourse);
        
        ecourseView = (WebView) findViewById(R.id.webview_ecourse);
        ecourseView.getSettings().setJavaScriptEnabled(true);
        ecourseView.loadUrl("http://www.mcatquestionaday.com/ecourse_iphone.php");
        ecourseView.setBackgroundColor(0x00000000);
    }
}
