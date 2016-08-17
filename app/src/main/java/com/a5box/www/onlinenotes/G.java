package com.a5box.www.onlinenotes;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;


public class G extends Application {
    public static ArrayList<StrucTask> tasks = new ArrayList<StrucTask>();

    public static Activity currentActivity;
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
