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
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;


public class Answer extends Activity{
	
	WebView resultsView;
	WebView bargraphView;
	TextView answerText;
	ImageView answerImage;
	
	String answerSubmitted;
	String date;
	int answerIsCorrect;

	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);
        
        //capture the graphic outlets
        resultsView = (WebView) findViewById(R.id.results_webview);
        bargraphView = (WebView) findViewById(R.id.bargraph_webview);
        answerText = (TextView) findViewById(R.id.answer_text);
        answerImage = (ImageView) findViewById(R.id.answer_image);
        
        //capture images
        Intent my_intent = getIntent();
    	
    	date = my_intent.getStringExtra("dates");
        
        //JSON
        JSONObject json = getJSONfromURL("http://www.mcatquestion.com/iPhoneX/resultsGraph.php?date="+ date);
        
        //get answer submitted from previous view
        Intent myIntent = getIntent();
        answerSubmitted = myIntent.getStringExtra("answer");
        
        
        try {
			if(json.getString("Correct").equals(answerSubmitted)){
				//answer is correct
				//show text saying correct
	        	answerText.setText("Correct!");
	        	
	        	//show green check mark image
	        	answerImage.setImageDrawable(getResources().getDrawable(R.drawable.accept));
	        	
	        	//show the results answer
	        	resultsView.loadUrl("http://www.mcatquestion.com/iPhoneX/checkAnswerAndDisplayResults.php?date="+ date);
	        	resultsView.setBackgroundColor(0x00000000);
	        	resultsView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

	        	
	        	//show the bargraph
	        	bargraphView.loadUrl("http://www.mcatquestion.com/iPhoneX/androidGraph.php?date="+ date);
	        	bargraphView.setBackgroundColor(0x00000000);
	        	bargraphView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

			}
			else{
				//answer is incorrect
				//show text saying incorrect
	        	answerText.setText("Sorry, Try Again!");
	        	resultsView.setBackgroundColor(0x00000000);
	        	bargraphView.setBackgroundColor(0x00000000);
	        	resultsView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
	        	bargraphView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);


	        	
	        	//show red X mark image
	        	answerImage.setImageDrawable(getResources().getDrawable(R.drawable.cancel));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
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
