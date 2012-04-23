package com.mcatquestion.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppPreferences {
     private static final String APP_SHARED_PREFS = "com.mcatquestion.preferences";
     private SharedPreferences appSharedPrefs;
     private Editor prefsEditor;
     private ArrayList<String> answered = new ArrayList<String>();

     public AppPreferences(Context context)
     {
         this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
         this.prefsEditor = appSharedPrefs.edit();
     }

     public String getUsername() {
         return appSharedPrefs.getString("username", "");
     }

     public void saveUsername(String text) {
         prefsEditor.putString("username", text);
         prefsEditor.commit();
     }
     
     public String getPassword() {
         return appSharedPrefs.getString("password", "");
     }

     public void savePassword(String text) {
         prefsEditor.putString("password", text);
         prefsEditor.commit();
     }
     
     public String getIsLoggedIn() {
         return appSharedPrefs.getString("is_logged_in", "");
     }

     public void saveIsLoggedIn(String text) {
         prefsEditor.putString("is_logged_in", text);
         prefsEditor.commit();
     }
     
     public void addToAnsweredQuestions(String text) {
    	answered.add(text);
     }
     
     public ArrayList<String> getAnsweredQuestions() {
    	 return answered;
     }
     
     public boolean isInAnsweredList(String date) {
    	 if(answered.contains(date)) {
    		 return true;
    	 }
    	 else {
    		 return false;
    	 }
     }
}
