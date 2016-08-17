package com.a5box.www.onlinenotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_main);

        final Map<String,String> params = new HashMap<>();
        params.put("title", "SangKop");
        params.put("desc", "Tramadol");

        findViewById(R.id.btnSendRequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebService webService = new WebService();
                String url = "http://192.168.185.2/onebox/insert.php";
//                String url = "http://192.168.185.2/onebox/insert.php?title=NewTitle&desc=Jadid";
                webService.connection(url,params);
            }
        });
    }
}