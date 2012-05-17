package com.mcatquestion.android;

import java.util.*;
import com.android.hardware.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Toast;

public class Previous extends Activity implements SensorEventListener{

	DatePicker dp;
	Date date;
	java.util.Date todayDate, startDate;
	String todaysDate;
	CheckBox physicsFilter,bioFilter,chemFilter,orgoFilter;
	String filters[] = new String[4]; 
	String randomURL;
	String randomDateReturned="0000-00-00";
	ImageButton submitButtonImage;

	Intent intent;
	String dateSelected;

	private ShakeListener mShaker;

	@Override
	public void onPause() {
		super.onPause();
		mShaker.setOnShakeListener(null);
	}
	@Override
	public void onResume() {
		super.onResume();
		mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
			public void onShake()
			{
				getRandomQuestion();
			}
		});    
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) { //
		super.onCreate(savedInstanceState);
		setContentView(R.layout.previous);

		final Context context = this;


		//capture items
		submitButtonImage = (ImageButton) findViewById(R.id.submit_button);

		submitButtonImage.setBackgroundColor(0x00000000);

		dp = (DatePicker) findViewById(R.id.datePicker1);

		physicsFilter = (CheckBox) findViewById(R.id.physics_checkbox);
		chemFilter = (CheckBox) findViewById(R.id.chem_checkbox);
		bioFilter = (CheckBox) findViewById(R.id.bio_checkbox);
		orgoFilter = (CheckBox) findViewById(R.id.orgo_checkbox);

		physicsFilter.setChecked(true);
		chemFilter.setChecked(true);
		bioFilter.setChecked(true);
		orgoFilter.setChecked(true);


		//set todays date for date picker comparison
		Calendar today = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("yyyy-MM-dd");
		todaysDate = df.format(today.getTime());
		todayDate = today.getTime();
		startDate = new Date(2008 - 1900,4,28);
		System.out.println(startDate);

		submitButtonImage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				date = new Date(dp.getYear() - 1900, dp.getMonth(), dp.getDayOfMonth());


				if((date.before(todayDate) || date.equals(todayDate)) && (date.after(startDate) || date.equals(startDate)))
				{
					SimpleDateFormat df = new SimpleDateFormat();
					df.applyPattern("yyyy-MM-dd");
					dateSelected = df.format(date);

					System.out.println(dateSelected);

					intent = new Intent(context, Question.class);
					intent.putExtra("date", dateSelected);
					intent.putExtra("prev", "YES");
					startActivity(intent);
				}
				else
				{
					Context context = getApplicationContext();
					CharSequence text = "Please select a valid date, and try again!";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();        
				}




			}
		});

		mShaker = new ShakeListener(this);
		mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
			public void onShake()
			{
				getRandomQuestion();
			}
		}); 
	}

	public void getRandomQuestion() {

		//go to random question with filters
		if(physicsFilter.isChecked())
		{
			filters[0] = "physics";
		}
		else
		{
			filters[0] = "NO";
		}
		if(chemFilter.isChecked())
		{
			filters[1] = "chemistry";
		}
		else
		{
			filters[1] = "NO";
		}
		if(bioFilter.isChecked())
		{
			filters[2] = "biology";
		}
		else
		{
			filters[2] = "NO";
		}
		if(orgoFilter.isChecked())
		{
			filters[3] = "orgo";
		}
		else
		{
			filters[3] = "NO";
		}

		if(!orgoFilter.isChecked() && !chemFilter.isChecked() && !bioFilter.isChecked() && !physicsFilter.isChecked()){
			//none are checked, toast a problem
			Context context = getApplicationContext();
			CharSequence text = "Please select atleast one subject filter";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();      
		}
		else{

			randomURL = "http://www.mcatquestionaday.com/iPhoneX/getRandomQuestion.php?bioFilter=" + filters[2] + "&ochemFilter=" + filters[3] + "&phyFilter=" + filters[0] + "&chemFilter=" + filters[1];

			//load random url
			System.out.println(randomURL);

			connectToServerAndReadData(randomURL);

			Context context = this;
			//set the date picker or just go to the question...
			intent = new Intent(context, Question.class);
			intent.putExtra("date", randomDateReturned);
			intent.putExtra("prev", "YES");
			//mShaker.setOnShakeListener(null);
			startActivity(intent);


		}
	}

	private void connectToServerAndReadData(String urlInput)
	{
		HttpURLConnection conn;
		boolean result = false;

		try{
			// Enter any URL here you want to connect
			URL url = new URL(urlInput);

			// Open a HTTP connection to the URL

			conn = (HttpURLConnection) url.openConnection();
			// conn.connect();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line ;



			while ((line = rd.readLine()) != null) {

				System.out.println("Readed Data from Server data- "+line);
				randomDateReturned = line;

			}



			rd.close();


		}catch(MalformedURLException e){

			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();               
		}
		catch(Exception e){
			e.printStackTrace();           
		}

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub

	}    
}
