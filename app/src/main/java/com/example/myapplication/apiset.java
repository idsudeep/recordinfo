package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiset {
    @FormUrlEncoded
    @POST("login.php")
    Call<responsedata>verify_user(
            @Field("email")String email,
            @Field("password")String password
    );

    @GET("fetchuser.php")
  Call<List<fetchuserdata>>getdata();
}
