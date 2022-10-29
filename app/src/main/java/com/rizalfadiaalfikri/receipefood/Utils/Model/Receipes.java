package com.rizalfadiaalfikri.receipefood.Utils.Model;

import com.google.gson.annotations.SerializedName;

public class Receipes {

    @SerializedName("response")
    private String response;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("receipe_id")
    private String receipe_id;


    @SerializedName("receipe_images")
    private String receipe_images;

    public String getResponse() {
        return response;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getReceipe_id() {
        return receipe_id;
    }

    public String getReceipe_images() {
        return receipe_images;
    }
}
