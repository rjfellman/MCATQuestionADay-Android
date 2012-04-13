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
import android.widget.ImageView;
import android.widget.TextView;

public class MyStats extends Activity{
	
	ImageView barchart1, barchart2, barchart3, barchart4;
	ImageView pctBG1,pctBG2,pctBG3,pctBG4;
	ImageView pctMark1, pctMark2, pctMark3, pctMark4;
	TextView barText1, barText2, barText3, barText4;
	TextView pctText1, pctText2, pctText3, pctText4;
	
	public static final String myStatsUrl = "http://www.mcatquestionaday.com/iPhoneX/getStats.php?userid=";
	String userID = "CF76C5DD-1FE6-5540-8E6C-BBDB7F62D578";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mystats);
        
        //get the text views
        barText1 = (TextView) findViewById(R.id.TextView01);
        barText2 = (TextView) findViewById(R.id.TextView09);
        barText3 = (TextView) findViewById(R.id.TextView06);
        barText4 = (TextView) findViewById(R.id.TextView07);
        
      //get the text views
        pctText1 = (TextView) findViewById(R.id.TextView04);
        pctText2 = (TextView) findViewById(R.id.TextView02);
        pctText3 = (TextView) findViewById(R.id.TextView03);
        pctText4 = (TextView) findViewById(R.id.textView1);
        
      //JSON
        JSONObject json = getJSONfromURL(myStatsUrl + userID);
        JSONArray jarray = null;

        ;
        ;
        	
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
			pctText1.setText("Physics - " + json.getString("PhysicsPercentile") + "th Percentile ");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			pctText2.setText("Biology - " + json.getString("BioPercentile") + "th Percentile");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			pctText3.setText("Chemistry - " + json.getString("ChemPercentile") + "th Percentile");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			pctText4.setText("Orgo - " + json.getString("OChemPercentile") + "th Percentile");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //get the image views
        barchart1 = (ImageView) findViewById(R.id.ImageView01);
        barchart2 = (ImageView) findViewById(R.id.ImageView02);
        barchart3 = (ImageView) findViewById(R.id.ImageView03);
        barchart4 = (ImageView) findViewById(R.id.ImageView04);
        
        //get the image views
        pctBG1 = (ImageView) findViewById(R.id.ImageView05);
        pctBG2 = (ImageView) findViewById(R.id.ImageView06);
        pctBG3 = (ImageView) findViewById(R.id.ImageView07);
        pctBG4 = (ImageView) findViewById(R.id.imageView9);
        
        //get the image views
        pctMark1 = (ImageView) findViewById(R.id.imageView1);
        pctMark2 = (ImageView) findViewById(R.id.imageView10);
        pctMark3 = (ImageView) findViewById(R.id.imageView11);
        pctMark4 = (ImageView) findViewById(R.id.imageView2);
        /*
        Bitmap bmp1=BitmapFactory.decodeResource(getResources(), R.drawable.horizbar);
        int width1=20;
        int height1=45;
        Bitmap resizedbitmap1=Bitmap.createScaledBitmap(bmp1, width1, height1, true);

        barchart1.setImageBitmap(resizedbitmap1);
        
        Bitmap bmp2=BitmapFactory.decodeResource(getResources(), R.drawable.horizbar);
        int width2=20;
        int height2=45;
        Bitmap resizedbitmap2=Bitmap.createScaledBitmap(bmp2, width2, height2, true);

        barchart2.setImageBitmap(resizedbitmap2);
        
        Bitmap bmp3=BitmapFactory.decodeResource(getResources(), R.drawable.horizbar);
        int width3=20;
        int height3=45;
        Bitmap resizedbitmap3=Bitmap.createScaledBitmap(bmp3, width3, height3, true);

        barchart3.setImageBitmap(resizedbitmap3);
        
        Bitmap bmp4=BitmapFactory.decodeResource(getResources(), R.drawable.horizbar);
        int width4=20;
        int height4=45;
        Bitmap resizedbitmap4=Bitmap.createScaledBitmap(bmp4, width4, height4, true);

        barchart4.setImageBitmap(resizedbitmap4);
		*/
        
        
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
