package com.example.myapplication;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnlogin;
    TextView createV , forgotPass ,vtitle;
    EditText loginEmail ,loginPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEmail = findViewById(R.id.loginEmail);
        loginPass = findViewById(R.id.loginPass);
        btnlogin = findViewById(R.id.loginBtn);
        createV = findViewById(R.id.createAccV);
        vtitle = findViewById(R.id.vtitle);
        forgotPass = findViewById(R.id.forgetPass);

        createV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newi = new Intent(MainActivity.this,regsiter_form.class);
                startActivity(newi);
            }
        });
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newf = new Intent(MainActivity.this,forgetpassword.class);
                startActivity(newf);
                finish();

            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getemail = loginEmail.getText().toString();
                String getpass = loginPass.getText().toString();

               // String reg = "[A-Za-z+_.]+@(.+pub)$";
                String email_format = "[A-Za-z+_.]+@(.+)$";
                Pattern pattern = Pattern.compile(email_format);
                Matcher matcher = pattern.matcher(getemail);
                if(getemail.equals("admin") && getpass.equals("admin")){

                    Intent i = new Intent(MainActivity.this,gateway.class);
                    startActivity(i);
                    finish();}
                if(matcher.matches()){ loginStart();}else{loginEmail.setText("");}



            }
        });


    }
    void loginStart(){
        String em = loginEmail.getText().toString();
        String pwd = loginPass.getText().toString();
        Call<responsedata>call = ctrl
                                 .getInstance()
                                 .getapi()
                                 .verify_user(em,pwd);

        call.enqueue(new Callback<responsedata>() {
            @Override
            public void onResponse(Call<responsedata> call, Response<responsedata> response) {
                responsedata somedata = response.body();
                String str = somedata.getMessage();
                Log.d(str, "onResponse: ");
                if(str.equals("True")){
                    Intent logi = new Intent(MainActivity.this,emp_listview.class);
                    startActivity(logi);
                    finish();
                }
                if(str.equals("false")){
                    vtitle.setText("Invalid input please check password or email");

                }

            }

            @Override
            public void onFailure(Call<responsedata> call, Throwable t) {
            vtitle.setText("login issue");
            }
        });
    }


}