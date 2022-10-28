package com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi;

import com.google.gson.annotations.SerializedName;
import com.rizalfadiaalfikri.receipefood.Utils.Model.Profile;

import java.util.List;

public class Users {

    @SerializedName("response")
    private String response;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("users")
    private List<Profile> profile;

    @SerializedName("users_name")
    private String user_name;

    @SerializedName("users_email")
    private String user_email;

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

    public List<Profile> getProfile() {
        return profile;
    }

    public void setProfile(List<Profile> profile) {
        this.profile = profile;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
