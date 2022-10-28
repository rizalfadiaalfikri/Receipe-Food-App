package com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi;

import com.rizalfadiaalfikri.receipefood.Utils.Model.Receipes;

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

    @FormUrlEncoded
    @POST("profil.php")
    Call<Users> getProfile(
            @Field("user_id") int user_id
    );

    @FormUrlEncoded
    @POST("update_profile.php")
    Call<Users> updateProfile(
            @Field("user_name") String user_name
    );

    @FormUrlEncoded
    @POST("add_receipes.php")
    Call<Receipes> addReceipes(
            @Field("users_id") int user_id,
            @Field("receipe_name") String receipe_name,
            @Field("receipe_ingredients") String receipe_ingredients,
            @Field("receipe_steps") String receipe_stpes
    );

}
