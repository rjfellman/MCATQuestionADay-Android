package com.mcatquestion.android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class MyStats extends Activity{
	
	ImageView barchart1;
	ImageView barchart2;
	ImageView barchart3;
	ImageView barchart4;
	ImageView pctBG1,pctBG2,pctBG3,pctBG4;
	ImageView pctMark1, pctMark2, pctMark3, pctMark4;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mystats);
        
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
        
        Bitmap bmp=BitmapFactory.decodeResource(getResources(), R.drawable.horizbar);
        int width=20;
        int height=45;
        Bitmap resizedbitmap=Bitmap.createScaledBitmap(bmp, width, height, true);

        barchart1.setImageBitmap(resizedbitmap);

        
        
    }
}
