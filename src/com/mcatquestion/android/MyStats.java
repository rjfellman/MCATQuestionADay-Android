package com.mcatquestion.android;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.view.MotionEvent;

public class MyStats extends Activity{
	
	WebView physicsPCT;
	WebView chemPCT;
	WebView bioPCT;
	WebView ochemPCT;
	WebView physicsScore,chemistryScore,biologyScore,orgoScore;
	TextView barText1,barText2,barText3,barText4;
	TextView pctText1,pctText2,pctText3,pctText4;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mystats);
        
        final AppPreferences preferences = new AppPreferences(this);
        
        System.out.println(preferences.getUsername());
        Log.i("Test",preferences.getUsername());
        
      //get the text views
        barText1 = (TextView) findViewById(R.id.barLabel1);
        barText2 = (TextView) findViewById(R.id.barLabel3);
        barText3 = (TextView) findViewById(R.id.barLabel2);
        barText4 = (TextView) findViewById(R.id.barLabel4);
        
      //get the text views
        pctText1 = (TextView) findViewById(R.id.pctLabel1);
        pctText2 = (TextView) findViewById(R.id.pctLabel3);
        pctText3 = (TextView) findViewById(R.id.pctLabel2);
        pctText4 = (TextView) findViewById(R.id.pctLabel4);
        
      //JSON
        JSONObject json = getJSONfromURL("http://www.mcatquestion.com/iPhoneX/getStats.php?userid="+preferences.getUsername());
	
        //set the text views
        try {
			barText1.setText(json.getString("PhysicsScore")+"%");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			barText2.setText(json.getString("BioScore")+"%");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			barText3.setText(json.getString("ChemScore")+"%");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			barText4.setText(json.getString("OrgoScore")+"%");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //set the text views
        try {
			pctText1.setText("You are currently performing better than "+json.getString("PhysicsPercentile")+"% of all users");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			pctText2.setText("You are currently performing better than "+json.getString("BioPercentile")+"% of all users");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			pctText3.setText("You are currently performing better than "+json.getString("ChemPercentile")+"% of all users");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			pctText4.setText("You are currently performing better than "+json.getString("OChemPercentile")+"% of all users");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
        
        physicsPCT.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {//
                return true;
            }
        });
        
        chemPCT.getSettings().setJavaScriptEnabled(true);
        chemPCT.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Percentiles.php?userid="+preferences.getUsername()+"&subject=chem");
        chemPCT.setBackgroundColor(0x00000000);
        
        chemPCT.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {//
                return true;
            }
        });
        
        bioPCT.getSettings().setJavaScriptEnabled(true);
        bioPCT.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Percentiles.php?userid="+preferences.getUsername()+"&subject=bio");
        bioPCT.setBackgroundColor(0x00000000);
        
        bioPCT.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {//
                return true;
            }
        });
        
        ochemPCT.getSettings().setJavaScriptEnabled(true);
        ochemPCT.loadUrl("http://www.mcatquestion.com/iPhoneX/MCAT-Percentiles.php?userid="+preferences.getUsername()+"&subject=ochem");
        ochemPCT.setBackgroundColor(0x00000000);
        
        ochemPCT.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {//
                return true;
            }
        });
        
    }
    
    public JSONObject getJSONfromURL(String url){

		//initialize
		InputStream is = null;
		String result = "";
		JSONObject jArray = null;

		//http post
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		}catch(Exception e){
			Log.e("log_tag", "Error in http connection "+e.toString());
		}

		//convert response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result=sb.toString();
		}catch(Exception e){
			Log.e("log_tag", "Error converting result "+e.toString());
		}

		//try parse the string to a JSON object
		try{
	        	jArray = new JSONObject(result);
		}catch(JSONException e){
			Log.e("log_tag", "Error parsing data "+e.toString());
		}

		return jArray;
	}
}
