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
        
        //UserID Generation
        String membershipID;
        
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "member-id.txt");
        try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		//try to read the content
		try{
			//open the file for reading
			FileInputStream fis = openFileInput("member-id.txt");
			fis.read();
			fis.close();
			
			char [] inputBuffer = new char[10];
			
			InputStreamReader isr;
			
			isr = new InputStreamReader(fis);
			
			isr.read(inputBuffer);
			
			membershipID = new String(inputBuffer);
							
			if(membershipID.equals("")){
				//do nothing, and use this later
				System.out.println("Print 1." + membershipID);
			}
			else{
				Random r = new Random();
				int i1 = r.nextInt(10-0)+0;
				int i2 = r.nextInt(10-0)+0;
				int i3 = r.nextInt(10-0)+0;
				int i4 = r.nextInt(10-0)+0;
				int i5 = r.nextInt(10-0)+0;
				int i6 = r.nextInt(10-0)+0;
				int i7 = r.nextInt(10-0)+0;
				int i8 = r.nextInt(10-0)+0;
				int i9 = r.nextInt(10-0)+0;
				int i10 = r.nextInt(10-0)+0;
				
				int[] memberID = new int[] {i1, i2, i3, i4, i5, i6, i7, i8, i9, i10};
				
				String str = Arrays.toString(memberID);
				StringBuffer sb = new StringBuffer();
				
				for(int i = 0; i < memberID.length; i++){
					sb.append(memberID[i]);
				}
				
				str = sb.toString();
				membershipID = str;
				System.out.println("Print X." + membershipID);
				
				//try to write the content
				try {
					//open the file
					FileOutputStream fos = openFileOutput("member-id.txt", Context.MODE_PRIVATE);
					fos.write(membershipID.getBytes());
					fos.close();
					
				} catch (java.io.IOException e) {
					//do something if an IOException occurs
					System.out.println("Print Badd");
				}
			}
			//close the file again
			fis.close();
		} catch (java.io.IOException e) {
			//do something if it doesn't exist
			System.out.println("Print Bad");
			}
		
		
        
        
        
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