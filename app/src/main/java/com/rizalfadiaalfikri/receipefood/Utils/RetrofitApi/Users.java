package com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi;

import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("response")
    private String response;

    @SerializedName("user_id")
    private String user_id;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
