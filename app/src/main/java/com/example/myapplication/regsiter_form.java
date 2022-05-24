package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regsiter_form extends AppCompatActivity {

   EditText rfname,rlname,rmname,remail,rpwd,raddress,rphone,rforpost,rdob,rdoj;
   Spinner rgender;
   Button rsignup;
    private  static final String url = "https://damp-depths-46466.herokuapp.com/school/forgetpas.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter_form);

        rfname = findViewById(R.id.fname);
        rlname = findViewById(R.id.lname);
        rmname = findViewById(R.id.mname);
        remail = findViewById(R.id.email);
        rpwd = findViewById(R.id.pwd);
        raddress = findViewById(R.id.address);
        rphone = findViewById(R.id.phone);
        rforpost = findViewById(R.id.forPost);
        rdob = findViewById(R.id.dob);
        rdoj = findViewById(R.id.doj);
        rgender = findViewById(R.id.gender);
        rsignup = findViewById(R.id.register_form_btn);

        rsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isblank = false;
                String fname = rfname.getText().toString();
                String lname = rlname.getText().toString();
                String mname= rmname.getText().toString();
                String email = remail.getText().toString();
                String pwd = rpwd.getText().toString();
                String address = raddress.getText().toString();
                String phone = rphone.getText().toString();
                String forpost = rforpost.getText().toString();
                String dob = rdob.getText().toString();
                String doj = rdoj.getText().toString();
                String gender = rgender.getSelectedItem().toString();
                String email_format = "[A-Za-z+_.]+@(.+)$";
                Pattern pattern = Pattern.compile(email_format);
                Matcher matcher = pattern.matcher(email);
                isblank = isemptyinput();
                Log.d(doj, "onClick: ");
                if(isblank){
                    if(!email.isEmpty() &&matcher.matches()){

            signupUsers(fname,lname,mname,email,pwd,address,phone,forpost,dob,doj,gender);
                    }else{
                        Toast.makeText(getApplicationContext(),"invalid Email",Toast.LENGTH_LONG).show();

                    }
                }
            }
        });


    }
    public  void signupUsers(final String fname , final String lname ,final String mname,final String email,final String password, final String address,final String phone ,final String forpost,final String dob, final String doj ,final String gender){
StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
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
    protected Map<String , String>getParams()throws AuthFailureError{
        Map<String , String>map = new HashMap<String,String>();
        map.put("fname",fname);
        map.put("lname",lname);
        map.put("mname",mname);
        map.put("email",email);
        map.put("password",password);
        map.put("address",address);
        map.put("phone",phone);
        map.put("forpost",forpost);
        map.put("dob",dob);
        map.put("doj",doj);
        map.put("gender",gender);
        return  map;
    }
};
    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
    queue.add(request);
    }
    private  boolean isemptyinput(){
        if(rfname.length() ==0){
            rfname.setError("first name required");
            return  false;
        }
        if(rlname.length()==0){rlname.setError("lastname required");}
        return true;
    }


}