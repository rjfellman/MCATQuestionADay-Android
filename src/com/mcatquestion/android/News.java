package com.mcatquestion.android;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.view.*;

public class News extends Activity{
	
	WebView newsView;
	Button newsSwitch; //
	int flag;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        
        newsView = (WebView) findViewById(R.id.webview_news);
        newsView.getSettings().setJavaScriptEnabled(true);
        newsView.loadUrl("http://www.mcatquestionaday.com/iPhoneX/getNews.php");
        newsView.setBackgroundColor(0x00000000);
        
        newsSwitch = (Button) findViewById(R.id.news_switch);
        newsSwitch.setText("Show All News");
        flag = 0;
        
        
        newsSwitch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(flag == 0){
                	//recent news is being shown
                	flag = 1;
                	newsView.loadUrl("http://www.mcatquestion.com/iPhoneX/allNews.php");
                	newsSwitch.setText("Show Recent News");
                } else {
                	//all news is being shown
                	flag = 0;
                	newsView.loadUrl("http://www.mcatquestion.com/iPhoneX/getNews.php");
                	newsSwitch.setText("Show All News");
                }
            }
        });

        
        
        
        
    }
}
