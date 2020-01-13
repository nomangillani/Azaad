package com.example.azaad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class FilterSessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "filterPreferences";

    // All Shared Preferences Keys


    // User name (make variable public to access from outside)
    public static final String MINAGE = "minage";
    public static final String MAXAGE = "maxage";
    private static final String CAST = "cast";
    private static final String MINHEIGHT = "minheight";
    private static final String MAXHEIGHT = "maxheight";
    private static final String EDUCATION = "education";
    // Email address (make variable public to access from outside)

    private static final String IS_LOGIN = "IsLoggedIn";
    // Constructor
    public FilterSessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void setfilter(String minage, String maxage, String minheight,String maxheight,String cast,String education){
        // Storing login value as TRUE
        editor.putString(MINAGE,minage);
        editor.putString(MAXAGE,maxage);
        editor.putString(MINHEIGHT,minheight);
        editor.putString(MAXHEIGHT,maxheight);
        editor.putString(EDUCATION,education);
        editor.putString(CAST,cast);
        editor.putBoolean(IS_LOGIN, true);
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

            // Staring Login Activity
            _context.startActivity(i);
           MainActivity.fa.finish();
        }

    }

    public   String  getMaxage ()
    {         pref =  _context . getSharedPreferences ( PREF_NAME , PRIVATE_MODE );
        return  pref.getString ( MAXAGE ,   "" );      }
    public   String  getMinage ()
    {         pref =  _context . getSharedPreferences ( PREF_NAME , PRIVATE_MODE );
        return  pref.getString ( MINAGE ,   "" );      }
    public   String  getMinheight ()
    {         pref =  _context . getSharedPreferences ( PREF_NAME , PRIVATE_MODE );
        return  pref.getString ( MINHEIGHT ,   "" );      }
    public   String  getMaxheight ()
    {         pref =  _context . getSharedPreferences ( PREF_NAME , PRIVATE_MODE );
        return  pref.getString ( MAXHEIGHT ,   "" );      }
    public   String  getEducation ()
    {         pref =  _context . getSharedPreferences ( PREF_NAME , PRIVATE_MODE );
        return  pref.getString ( EDUCATION ,   "" );      }
    public String getCast ()
    {         pref =  _context . getSharedPreferences ( PREF_NAME , PRIVATE_MODE );
        return  pref.getString ( CAST ,   "" );      }



    /**
     * Get stored session data
     * */
   /* public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }*/

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
        MainActivity.fa.finish();
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}