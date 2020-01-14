package com.eltiempo.mobiletest.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Vars {

    private static final String BASE_URL = "https://images-api.nasa.gov/";
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).build();
    public final static Retrofit getRetrofitInstance = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();

    public static void setSharedPreferences(Activity activity, String key, String value) {
        SharedPreferences prefs = activity.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getSharedPreferences(Activity activity, String key) {
        SharedPreferences prefs = activity.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }
}
