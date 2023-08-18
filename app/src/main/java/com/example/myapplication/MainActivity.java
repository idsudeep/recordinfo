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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;

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
                if(matcher.matches()){ loginStart( getemail.toString(),getpass.toString());}else{loginEmail.setText("");}



            }
        });



    }
    private void loginStart(String email, String password) {
        String apiURL = "https://osseous-assembly.000webhostapp.com/school/login.php";

        // Create a StringRequest using Volley
        StringRequest request = new StringRequest(Request.Method.POST, apiURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResult = new JSONObject(response);
                            boolean success = jsonResult.getBoolean("success");
                            String message = jsonResult.getString("message");

                            if (success) {
                                // Login successful, navigate to the main activity

                                Intent i = new Intent(MainActivity.this,gateway.class);
                                startActivity(i);
                                finish();
                                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                // Add your navigation logic here
                            } else {
                                // Login failed, show an error message
                                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle network or API error
                        Toast.makeText(MainActivity.this, "Error connecting to server", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                // Add parameters to the POST request
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        // Add the request to the Volley request queue
        Volley.newRequestQueue(this).add(request);
    }

}




