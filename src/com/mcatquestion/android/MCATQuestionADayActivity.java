package com.mcatquestion.android;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Random;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageButton;
import android.view.*;

public class MCATQuestionADayActivity extends Activity {
	
	ImageButton todaysQuestionButton;
	ImageButton prevQuestionButton;
	ImageButton myStatsButton;
	ImageButton eLearningButton;
	ImageButton eCourseButton;
	ImageButton newsButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        AppPreferences preferences = new AppPreferences(this);
        System.out.println(preferences.getUsername());
        System.out.println(preferences.getPassword());
        System.out.println(preferences.getIsLoggedIn());
        
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
       		
        		Intent intent;
        		intent = new Intent(context, MyStats.class);
                startActivity(intent);
           }
       });
       
      eLearningButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
       		
        		Intent intent;
        		intent = new Intent(context, eLearning.class);
                startActivity(intent);
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
}