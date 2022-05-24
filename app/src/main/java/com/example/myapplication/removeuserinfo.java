package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class removeuserinfo extends AppCompatActivity {
    private static final String rurl ="https://damp-depths-46466.herokuapp.com/school/removeUser.php";
    Button rbtn;
    EditText rnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_removeuserinfo);

    rbtn = findViewById(R.id.btn_remove);
    rnumber = findViewById(R.id.remove_number);

    rbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String getnumber = rnumber.getText().toString();

            removeuser(getnumber);


        }
    });

    }
      void removeuser(final String rnumber){

          StringRequest request = new StringRequest(Request.Method.POST, rurl, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                  Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                  Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_LONG).show();
              }
          }){
              @Nullable
              @Override
              protected  Map<String,String>getParams() throws AuthFailureError{

                  Map<String,String> map = new HashMap<>();
                  map.put("mobileno",rnumber);
                  return map;
              }
          };
          RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
          queue.add(request);

    }
}