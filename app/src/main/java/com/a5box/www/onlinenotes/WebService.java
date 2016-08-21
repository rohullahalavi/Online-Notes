package com.a5box.www.onlinenotes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class WebService {



    public void connection(String webLink, final String service, final Map<String, String> params) {
        if (isNetworkAvailable()) {



        final String url = webLink + service;
        final RequestQueue requestQueue = Volley.newRequestQueue(G.context);
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (service.equals("read")) {

                            try {
                                JSONArray jsonArray = new JSONArray(response);

                                ActivityMain.tasks.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    StrucTask task = new StrucTask();
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    task.setTitle(object.getString("title"));
                                    task.setDescription(object.getString("desc"));

                                    task.setDone(object.getInt("done") == 1);

                                    task.setId(object.getInt("id"));
                                    ActivityMain.tasks.add(task);

                                }
                                if (ActivityMain.refreshLayout.isRefreshing()) {
                                    ActivityMain. adapter.notifyDataSetChanged();
                                    ActivityMain.refreshLayout.setRefreshing(false);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "Eroorrrrrrrrr");

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };


        requestQueue.add(stringRequest);
    }else {
            Toast.makeText(G.context, "Network Is Not Available !", Toast.LENGTH_SHORT).show();
        }

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) G.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isConnect = false;
        if (networkInfo != null) {
            isConnect = true;
        }
        return isConnect;
    }

}
