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

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import android.util.Log;
import android.view.*;

public class eLearning extends ListActivity{
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	  final Context context = this;
	  
	  String userID = "CF76C5DD-1FE6-5540-8E6C-BBDB7F62D578";
	  
	//JSON
     JSONObject json = getJSONfromURL("http://www.mcatquestion.com/iPhoneX/eLearningAndroid.php?userid="+ userID);
     JSONArray jarray = null;
	try {
		jarray = json.getJSONArray("android_list");
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     

     String[] mStrings = new String[jarray.length()];

     for (int i=0; i<jarray.length(); i++)
     {
         try {
			mStrings[i] = jarray.getString(i);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
	  setListAdapter(new ArrayAdapter<String>(this, R.layout.elearning, mStrings));

	  ListView lv = getListView();
	  lv.setTextFilterEnabled(true);

	  lv.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {
	      // When clicked, show a toast with the TextView text
	      //Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
	          //Toast.LENGTH_SHORT).show();
	      Intent intent;
	      
	      String dateSelected = (String) ((TextView) view).getText();	      
	      intent = new Intent(context, Question.class);
			intent.putExtra("date", dateSelected);
			intent.putExtra("prev", "YES");
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
