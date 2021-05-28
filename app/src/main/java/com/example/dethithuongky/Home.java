
package com.example.dethithuongky;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Home extends AppCompatActivity {

    private Button btnGet;
    private TextView tvDisplay;
    String url ="https://60b095981f26610017ffe8a1.mockapi.io/api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnGet = (Button) findViewById(R.id.btnGet);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay) ;

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetData(url);
            }

            private void GetData(String url) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        tvDisplay.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home.this,
                                "Error make by API server!", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(Home.this);
                requestQueue.add(stringRequest);
            }
        });
    }
}