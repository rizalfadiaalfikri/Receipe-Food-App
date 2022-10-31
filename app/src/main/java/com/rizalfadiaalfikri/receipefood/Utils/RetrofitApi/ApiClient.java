package com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // http://10.0.3.2/receipe_food/
    public static String BASE_URL = "https://juggernaut28.000webhostapp.com/";
    public static Retrofit retrofit = null;

    public static Retrofit getClientApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
