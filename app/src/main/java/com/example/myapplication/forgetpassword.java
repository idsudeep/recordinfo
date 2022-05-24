package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class forgetpassword extends AppCompatActivity {
    private  static final String furl = "https://damp-depths-46466.herokuapp.com/school/forgetpass.php";
CheckBox checkbtn;
EditText fem,fpas,fnewpas ,fd;
Button btnf;
TextView fv ,glog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        glog = findViewById(R.id.glogin);
        checkbtn = findViewById(R.id.checkbox_btn);
        fem = findViewById(R.id.femail);
        fpas= findViewById(R.id.newpass);
        fnewpas = findViewById(R.id.confirm_pass);
        fd = findViewById(R.id.fdob);
        btnf = findViewById(R.id.forget_btn);
        fv = findViewById(R.id.fview);

        glog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(forgetpassword.this,MainActivity.class);
                startActivity(i);
            }
        });
        checkbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b) {
                    fpas.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    fnewpas.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    fpas.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    fnewpas.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });

        btnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String femail= fem.getText().toString();
                String dob = fd.getText().toString();
                String pas = fpas.getText().toString();
                String npass = fnewpas.getText().toString();
                String email_format = "[A-Za-z+_.]+@(.+)$";
                Pattern pattern = Pattern.compile(email_format);
                Matcher matcher = pattern.matcher(femail);

                if(dob.isEmpty()){ fd.setError("input missing");}
                if(matcher.matches()){
                    if(pas.equals(npass) && !pas.isEmpty() && !npass.isEmpty()) {
                    forgetPass(femail,pas,dob);
                    }else{
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                fv.setText("please re-enter the input");
                            }
                        },300);
                        fv.setText("Password not Match");
                        fem.setText("");
                        fpas.setText("");
                        fnewpas.setText("");
                        fd.setText("");

                    }
                }else{fem.setError("invalid Email");}
            }
        });

    }
    public  void forgetPass(final String femail , final  String password , final String dob){

        StringRequest request = new StringRequest(Request.Method.POST, furl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String ,String>getParams() throws AuthFailureError{
                Map<String ,String>map = new HashMap<>();
                map.put("email",femail);
                map.put("password",password);
                map.put("dob",dob);
                return map;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}