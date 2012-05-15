package com.mcatquestion.android;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.*;

public class MCATQuestionADayActivity extends Activity {
	
	ImageButton todaysQuestionButton;
	ImageButton prevQuestionButton;
	ImageButton myStatsButton;
	ImageButton eLearningButton;
	ImageButton eCourseButton;
	ImageButton newsButton;
	
	TextView todaysDate;
	
	private boolean isNetworkConnected() {
		  ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		  NetworkInfo ni = cm.getActiveNetworkInfo();
		  if (ni == null) {
		   // There are no active networks.
		   return false;
		  } else
		   return true;
		 }
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        AppPreferences preferences = new AppPreferences(this);
        
        todaysDate = (TextView) findViewById(R.id.mainScreenDate);
        //Set todays date in Month Day, Year format
        Calendar today = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("  MMMM dd, yyyy  ");
        String todaysDateMarker;
        todaysDateMarker = df.format(today.getTime());
        todaysDate.setText(todaysDateMarker);
        
        //For testing only!
        //preferences.saveUsername("TempReset");

        if (!preferences.getUsername().contains("droid")) {
        	//username hasnt been created yet
        	//create one and save it to defaults
        	
        	preferences.saveUsername(generateUserNameAndSave());
        	System.out.println("Saving username" + preferences.getUsername());
        	Log.i("user", "Saving username" + preferences.getUsername());
        	//
        	//create an entry online
        	String urlUsername = "http://www.mcatquestionaday.com/iPhoneX/admin/checkIfUserExists.php?userid="+preferences.getUsername();
    		JSONObject jsonUser = getJSONfromURL(urlUsername);  	
        }
        
        final Context context = this;
        
        todaysQuestionButton = (ImageButton) findViewById(R.id.imageButton1);
        prevQuestionButton = (ImageButton) findViewById(R.id.imageButton2);
        myStatsButton = (ImageButton) findViewById(R.id.imageButton3);
        eLearningButton = (ImageButton) findViewById(R.id.imageButton4);
        eCourseButton = (ImageButton) findViewById(R.id.imageButton5);
        newsButton = (ImageButton) findViewById(R.id.imageButton6);
  
        int alphaVal = 0;
        
        todaysQuestionButton.getBackground().setAlpha(alphaVal);
        prevQuestionButton.getBackground().setAlpha(alphaVal);
        myStatsButton.getBackground().setAlpha(alphaVal);
        eLearningButton.getBackground().setAlpha(alphaVal);
        eCourseButton.getBackground().setAlpha(alphaVal);
        newsButton.getBackground().setAlpha(alphaVal);
        
       todaysQuestionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		
         		Intent intent;
         		intent = new Intent(context, Question.class);
    			intent.putExtra("prev", "NO");
                startActivity(intent);
            }
        });
       
       prevQuestionButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
       		
        		Intent intent;
        		intent = new Intent(context, Previous.class);
                startActivity(intent);
           }
       });
       
       myStatsButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
       		if(isNetworkConnected()){
       			Intent intent;
        		intent = new Intent(context, MyStats.class);
                startActivity(intent);
       		}
       		else {
       			Context context = getApplicationContext();
				CharSequence text = "Internet Connection Required for this area!";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();   
       		}
           }
       });
       
      eLearningButton.setOnClickListener(new View.OnClickListener() {
    	  public void onClick(View v) {
         		if(isNetworkConnected()){
         			Intent intent;
          		intent = new Intent(context, eLearning.class);
                  startActivity(intent);
         		}
         		else {
         			Context context = getApplicationContext();
  				CharSequence text = "Internet Connection Required for this area!";
  				int duration = Toast.LENGTH_SHORT;

  				Toast toast = Toast.makeText(context, text, duration);
  				toast.show();   
         		}
             }
       });
       
       eCourseButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
       		
        		Intent intent;
        		intent = new Intent(context, eCourse.class);
                startActivity(intent);
           }
       });
       
       newsButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
       		
        		Intent intent;
        		intent = new Intent(context, News.class);
                startActivity(intent);
           }
       });
}

	private String generateUserNameAndSave() {
		// TODO Auto-generated method stub
		String username = "droid";
		Random rand = new Random();
		Integer adder;
		//
		for (Integer i =0; i<35; i++){
			adder = rand.nextInt(9 - 0 + 1) + 0;
			username = username + adder.toString(); 
		}
		return username;
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