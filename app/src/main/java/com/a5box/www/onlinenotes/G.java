package com.a5box.www.onlinenotes;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;


public class G extends Application {
    public static ArrayList<StrucTask> tasks = new ArrayList<StrucTask>();
    public static LayoutInflater inflater;
    public static Activity currentActivity;
    public static Context context;
    public static WebService service = new WebService();
    @Override
    public void onCreate() {
        super.onCreate();
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = getApplicationContext();
    }
}
