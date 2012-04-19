package com.mcatquestion.android;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class MyStats extends Activity{
	
	WebView barcharts, percentile;
	
	public static final String myStatsUrl = "http://www.mcatquestionaday.com/iPhoneX/getStats.php?userid=";
	String userID = "CF76C5DD-1FE6-5540-8E6C-BBDB7F62D578";
	
	public static final String barchartsURL= "http://www.mcatquestionaday.com/iPhoneX/MCAT-Bargraphs-Stats.html";
	public static final String pctURL= "http://www.mcatquestionaday.com/iPhoneX/MCAT-Percentiles.html";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mystats);
        
        barcharts = (WebView) findViewById(R.id.webview_stats);
        percentile = (WebView) findViewById(R.id.webview_statsPct);
        
        barcharts.getSettings().setJavaScriptEnabled(true);
        barcharts.loadUrl(barchartsURL);
        barcharts.setBackgroundColor(0x00000000);
        barcharts.setScrollContainer(false);
        
        percentile.getSettings().setJavaScriptEnabled(true);
        percentile.loadUrl(pctURL);
        percentile.setBackgroundColor(0x00000000);
        percentile.setScrollContainer(false);
        
    }
}
