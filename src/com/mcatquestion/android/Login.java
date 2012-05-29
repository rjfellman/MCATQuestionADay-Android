package com.mcatquestion.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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
import android.util.Log;
import android.view.View;

import android.widget.*;

public class Login extends Activity{
	
	Intent intent;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        Button loginButton, registerButton;
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.register);
        
        final Context context = this;
        
        final AppPreferences preferences = new AppPreferences(this);
        
        registerButton.setOnClickListener(new View.OnClickListener() {
        	 public void onClick(View v) {
        		//proceed to registration page
     			intent = new Intent(context, Registration.class);
                 startActivity(intent);
        	 }
        });
        
        //if they are logged in, then proceed to main menu
        System.out.println(preferences.getIsLoggedIn());
        String isLoggedIn = preferences.getIsLoggedIn();
        if (isLoggedIn.equalsIgnoreCase("YES")){
        	//proceed to main menu
			intent = new Intent(context, MCATQuestionADayActivity.class);
            startActivity(intent);
        }
        else{
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	System.out.println("I was clicked");
            	//submit username and password
            	EditText userNameField = (EditText) findViewById(R.id.usernameField);
                EditText passwordField = (EditText) findViewById(R.id.passwordField);
            	
            	String sendUser = userNameField.getText().toString();
            	String sendPass = passwordField.getText().toString();
            	String response = "";
            	
            	//
            	JSONObject json = getJSONfromURL("http://www.mcatquestion.com/iPhoneX/loginResponse.php?userID="+ sendUser + "&password=" + sendPass);
            	try {
					response = (String) json.getString("login_response");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	CheckBox rememberMe = (CheckBox) findViewById(R.id.rememberMe);
            	if(response.equalsIgnoreCase("YES")){
            		if(rememberMe.isChecked()){
            			preferences.saveIsLoggedIn("YES");
            		}
            		else{
                    	preferences.saveIsLoggedIn("NO");
                    }
            		//TODO: create user in iPhoneAppStats
            		String urlUsername = "http://www.mcatquestionaday.com/iPhoneX/admin/checkIfUserExists.php?userid="+sendUser;
            		JSONObject jsonUser = getJSONfromURL(urlUsername);
            		URL url = null;
					try {
						url = new URL(urlUsername);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		try {
						url.openConnection ();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		preferences.saveUsername(sendUser);
            		preferences.savePassword(sendPass);
    				intent = new Intent(context, MCATQuestionADayActivity.class);
                    startActivity(intent);
                    
            	}
            	else{
            		//display a login incorrect toast
            		System.out.println("Incorrect Login Info");
            		Context context = getApplicationContext();
        			CharSequence text = "Unable to locate this username/password in our records, please try again!";
        			int duration = Toast.LENGTH_SHORT;

        			Toast toast = Toast.makeText(context, text, duration);
        			toast.show(); 
            	}
            	
            }
        });
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
