package com.example.cfm.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cfm on 15-12-8.
 */
public class ApplicationConfig {
    private static ApplicationConfig applicationConfig;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public void setUploadInterval(long uploadInterval) {
        editor.putLong("uploadInterval", uploadInterval);
    }

    public void setRefreshInterval(long refreshInterval) {
        editor.putLong("refreshInterval", refreshInterval);
    }

    public void setControlInterval(long controlInterval) {
        editor.putLong("controlInterval", controlInterval);
    }

    public long getUploadInterval(){
        long uploadInterval = sharedPreferences.getLong("uploadInterval", 3000);
        return uploadInterval;
    }

    public long getRefreshInterval(){
        long refreshInterval = sharedPreferences.getLong("refreshInterval", 3000);
        return refreshInterval;
    }

    public long getControlInterval(){
        long controlInterval = sharedPreferences.getLong("controlInterval", 3000);
        return controlInterval;
    }

    private ApplicationConfig(Context context){
        this.sharedPreferences = context.getSharedPreferences("preferences",Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public static ApplicationConfig getInstance(Context context){
        if (applicationConfig == null){
            applicationConfig = new ApplicationConfig(context);
        }
        return applicationConfig;
    }

    public  boolean save(){
        return editor.commit();
    }

}
