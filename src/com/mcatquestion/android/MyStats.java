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
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyStats extends Activity{
	
	WebView barcharts, percentile;
	
	public static final String myStatsUrl = "http://www.mcatquestionaday.com/iPhoneX/getStats.php?userid=";
	public static final String barchartsURL= "http://www.mcatquestionaday.com/iPhoneX/MCAT-Bargraphs-Stats.html";
	public static final String pctURL= "http://www.mcatquestionaday.com/iPhoneX/MCAT-Percentiles.html";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mystats);
        
        final AppPreferences preferences = new AppPreferences(this);
        String userID = preferences.getUsername();
		//TODO try unhandled exception here;
        
        TextView userLabel = (TextView) findViewById(R.id.usernameStats);
        userLabel.setText("Stats for "+userID);
                
        //Intent achartIntent = new MyStatsChart().execute(this);
        //startActivity(achartIntent);
        
    }
}
