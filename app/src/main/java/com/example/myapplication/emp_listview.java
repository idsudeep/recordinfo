package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class emp_listview extends AppCompatActivity {
    RecyclerView rrecview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_listview);
        rrecview = findViewById(R.id.recview);
        rrecview.setLayoutManager(new LinearLayoutManager(this));
        loaddata();

    }
    public void loaddata(){
        Call<List<fetchuserdata>> call = ctrl
                .getInstance()
                .getapi()
                .getdata();
        call.enqueue(new Callback<List<fetchuserdata>>() {
            @Override
            public void onResponse(Call<List<fetchuserdata>> call, Response<List<fetchuserdata>> response) {
                List<fetchuserdata>data = response.body();
                myadapter adapter = new myadapter(data);
                rrecview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<fetchuserdata>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }
}