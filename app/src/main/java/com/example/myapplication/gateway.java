package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gateway extends AppCompatActivity {

    Button userbtn , btnruser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gateway);

        userbtn = findViewById(R.id.btn_userinfo);
        btnruser = findViewById(R.id.btn_del_user_info);

        userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(gateway.this,emp_listview.class);
                startActivity(i);

            }
        });
    btnruser.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(gateway.this,removeuserinfo.class);
            startActivity(i);

        }
    });
    }

}