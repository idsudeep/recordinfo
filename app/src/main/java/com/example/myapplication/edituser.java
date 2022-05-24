package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class edituser extends AppCompatActivity {
    private  static final String updateurl = "https://damp-depths-46466.herokuapp.com/school/update_school_user.php";
   EditText en,em,ep,ea,epos,ej,eln;
   Button btnupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edituser);

        en= findViewById(R.id.ename);
        eln= findViewById(R.id.el);
        em = findViewById(R.id.eemail);
        ep = findViewById(R.id.emobile);
        ea = findViewById(R.id.eaddress);
        epos = findViewById(R.id.epost);
        ej = findViewById(R.id.edoj);
        btnupdate = findViewById(R.id.update_btn);
        Intent i = getIntent();

        en.setText(i.getStringExtra("fname"));
        eln.setText(i.getStringExtra("lname"));
        ep.setText(i.getStringExtra("mobileno"));
        em.setText(i.getStringExtra("email"));
        ea.setText(i.getStringExtra("address"));
        epos.setText(i.getStringExtra("post"));
        ej.setText(i.getStringExtra("doj"));
        String useri = i.getStringExtra("userid");
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fn = en.getText().toString();
                String el =eln.getText().toString();
                String ph = ep.getText().toString();
                String ema = em.getText().toString();
                String add = ea.getText().toString();
                String pos = epos.getText().toString();
                String doj= ej.getText().toString();

                Log.d(useri, "onClick: ");
                updateUser(useri,fn,el,ph,ema,add,pos,doj);
            }
        });




    }
    public void updateUser(final String userid, final String fname,final String elname,final String mobileno, final String email, final String address ,final String post ,final String doj){

        StringRequest request = new StringRequest(Request.Method.POST, updateurl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();
                Intent i= new Intent(edituser.this,gateway.class);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String,String>getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("userid",userid);
                map.put("fname",fname);
                map.put("lname",elname);
                map.put("mobileno",mobileno);
                map.put("email",email);
                map.put("address",address);
                map.put("post",post);
                map.put("doj",doj);
                return  map;

            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}