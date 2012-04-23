package com.mcatquestion.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import android.os.Bundle;
import android.webkit.WebView;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class Question extends Activity{
	
	WebView questionView;
	WebView answerA;
	RadioGroup answerGroup;
	Button submitButton;
	TextView dateLabel;
	
	String answerSelected = "";
	String date = "";
	String dateForLabel;
	String isPrev;
	
	Intent intent;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        
        final Context context = this;
        final AppPreferences preferences = new AppPreferences(this);
        final ArrayList answered = preferences.getAnsweredQuestions();
        
        //if today's date
                
        Intent my_intent = getIntent();
        
        isPrev = my_intent.getStringExtra("prev");
        
        if(isPrev.equals("YES"))
        {
        	date=my_intent.getStringExtra("date");    
        }
        else{            
            Calendar today = new GregorianCalendar();
            SimpleDateFormat df = new SimpleDateFormat();
            df.applyPattern("yyyy-MM-dd");
            date = df.format(today.getTime());  
            //date=my_intent.getStringExtra("date");
        }
        
        System.out.println(date);
        
        dateLabel = (TextView) findViewById(R.id.date_display);
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyyy-MM-dd");
        Date temp = new Date();
        dateForLabel = date;
        try {
			temp = df.parse(dateForLabel);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        df.applyPattern("MMM dd, yyyy");
        dateForLabel = df.format(temp);
        dateLabel.setText(dateForLabel);
        
        
        questionView = (WebView) findViewById(R.id.webview_question);
        questionView.getSettings().setJavaScriptEnabled(true);
        questionView.loadUrl("http://www.mcatquestion.com/iPhoneX/getSelectedQuestion.php?date="+ date);
        questionView.setBackgroundColor(0x00000000);
        
        answerA = (WebView) findViewById(R.id.webview_answer);
        answerA.getSettings().setJavaScriptEnabled(true);
        answerA.setBackgroundColor(0x00000000);
        
        answerGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        //RadioButton checkedRadioButton = (RadioButton)answerGroup.findViewById(answerGroup.getCheckedRadioButtonId());
        answerA.loadUrl("http://www.mcatquestion.com/iPhoneX/getAnswers.php?date="+ date + "&dataReq=A");
        
        answerGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        	public void onCheckedChanged(RadioGroup group, int checkedId)
            {
        		switch(checkedId) {
        		
        		case R.id.radio0 :
        			answerA.loadUrl("http://www.mcatquestion.com/iPhoneX/getAnswers.php?date="+ date + "&dataReq=A");
        			answerSelected = "A";
        			break;
        			
        		case R.id.radio1 :
        			answerA.loadUrl("http://www.mcatquestion.com/iPhoneX/getAnswers.php?date="+ date + "&dataReq=B");
        			answerSelected = "B";
        			break;
        			
        		case R.id.radio2 :
        			answerA.loadUrl("http://www.mcatquestion.com/iPhoneX/getAnswers.php?date="+ date + "&dataReq=C");
        			answerSelected = "C";
        			break;
        			
        		case R.id.radio3 :
        			answerA.loadUrl("http://www.mcatquestion.com/iPhoneX/getAnswers.php?date="+ date + "&dataReq=D");
        			answerSelected = "D";
        			break;
        		
        		}
            }
        });
        answerA.setBackgroundColor(0x00000000);
        
        submitButton = (Button) findViewById(R.id.submit_answer);
        submitButton.setText("Submit");
        
        
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if(preferences.isInAnsweredList(date)){
            		//then it was already answered, dont log stats
            		String answerToCloud = "http://www.mcatquestionaday.com/iPhoneX/checkAnswer.php?date="+date+"&answer="+answerSelected+"&userid="+preferences.getUsername()+"&didAnswer=YES";
            		JSONObject json = getJSONfromURL(answerToCloud);
            	}
            	else {
            		//add it to list and log stats
            		preferences.addToAnsweredQuestions(date);
            		String answerToCloud = "http://www.mcatquestionaday.com/iPhoneX/checkAnswer.php?date="+date+"&answer="+answerSelected+"&userid="+preferences.getUsername()+"&didAnswer=NO";
            		JSONObject json = getJSONfromURL(answerToCloud);
            	}
            	
            	intent = new Intent(context, Answer.class);
            	
				intent.putExtra("answer", answerSelected);
				intent.putExtra("dates", date);
                startActivity(intent);
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
