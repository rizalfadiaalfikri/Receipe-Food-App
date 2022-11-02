package com.rizalfadiaalfikri.receipefood.Utils.Session;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    private static final String USER_ID = "USER_ID";
    private static final String INGREDIENTS = "INGREDIENTS";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String user_id) {
        editor.putBoolean(LOGIN, true);
        editor.putString(USER_ID, user_id);
        editor.apply();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(USER_ID, sharedPreferences.getString(USER_ID, null));
        return user;
    }

//    public ArrayList<Integer> getUserId() {
//        ArrayList<Integer> user = new ArrayList<>();
//        user.add(sharedPreferences.getInt(USER_ID, 0));
//        return user;
//    }

    public String getUserId() {
        String userId = sharedPreferences.getString(USER_ID, null);
        return userId;
    }

    public void createIngredients(String ingredients) {
        editor.putString(INGREDIENTS, ingredients);
        editor.apply();
    }

    public String getIngredients() {
        String ingredients = sharedPreferences.getString(INGREDIENTS, null);
        return ingredients;
    }
}
