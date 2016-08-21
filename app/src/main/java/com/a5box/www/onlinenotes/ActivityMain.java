package com.a5box.www.onlinenotes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityMain extends Activity {
    public static final String URL ="http://192.168.185.2/onebox/service?action=";
    public static ArrayList<StrucTask> tasks = new ArrayList<StrucTask>();
    public static CustomDialog cDialog = new CustomDialog();
    public static ArrayAdapter<StrucTask> adapter = new AdapterNote(tasks);
    public static   SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_main);
        ListView lstContent = (ListView) findViewById(R.id.lstContent);



        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                G.service.connection(URL,"read",null);

            }
        });

        lstContent.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        G.currentActivity = this;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,1,"Add");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //  Adding new task
            case 1:
                int lastId =0;
                if (tasks.size()!=0) {
                    for (StrucTask task:tasks) {
                        if (lastId<task.getId()) {
                            lastId=task.getId();}}}
                cDialog.customDialog(0,"insert",lastId);
                break;


        }


        return super.onOptionsItemSelected(item);
    }
}