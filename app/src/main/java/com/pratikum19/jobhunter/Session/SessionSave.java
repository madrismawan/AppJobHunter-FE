package com.pratikum19.jobhunter.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionSave {
    private static Context context;


    public static void saveSession(Context context, String token, String role){
        SharedPreferences saveSession = context.getSharedPreferences("SESSION", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveSession.edit();
        editor.putString("token", "Bearer "+token);
        editor.putString("role", role);
        editor.commit();
    }

    public static void deleteSession(Context context){
        SharedPreferences deleteSession = context.getSharedPreferences("SESSION", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = deleteSession.edit();
        editor.remove("token").commit();
        editor.remove("role").commit();
    }

}


