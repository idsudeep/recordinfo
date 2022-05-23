package com.example.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ctrl {
    private static final String url = "https://damp-depths-46466.herokuapp.com/school/";
    private  static  ctrl clientobj;
    private  static Retrofit retrofit;
    ctrl(){
        retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized ctrl getInstance(){
        if (clientobj==null)
            clientobj = new ctrl();
        return clientobj;

    }
    apiset getapi(){return retrofit.create(apiset.class);}
}
