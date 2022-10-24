package com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("users/registration.php")
    Call<Users> registration(
            @Field("user_name") String user_name,
            @Field("user_email") String user_email,
            @Field("user_password") String user_password
    );

    @FormUrlEncoded
    @POST("users/login.php")
    Call<Users> login(
            @Field("user_email") String user_email,
            @Field("user_password") String user_password
    );

}
