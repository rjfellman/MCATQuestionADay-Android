package com.mcatquestion.android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MyStats extends Activity{
	
	ImageView barchart1, barchart2, barchart3, barchart4;
	ImageView pctBG1,pctBG2,pctBG3,pctBG4;
	ImageView pctMark1, pctMark2, pctMark3, pctMark4;
	TextView barText1, barText2, barText3, barText4;
	TextView pctText1, pctText2, pctText3, pctText4;
	
	public static final String myStatsUrl = "http://www.mcatquestionaday.com/iPhoneX/getStats.php?userid=";
	
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
        
        //set the text views
        barText1.setText("98%");
        barText2.setText("68%");
        barText3.setText("48%");
        barText4.setText("28%");
        
        //set the text views
        pctText1.setText("Physics - 87th Percentile ");
        pctText2.setText("Biology - 27th Percentile");
        pctText3.setText("Chemistry - 45th Percentile");
        pctText4.setText("Orgo - 17th Percentile");
        
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
}
