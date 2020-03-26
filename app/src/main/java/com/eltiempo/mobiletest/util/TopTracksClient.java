package com.eltiempo.mobiletest.util;

import com.eltiempo.mobiletest.model.TopTracks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopTracksClient {

    @GET("/2.0/")
    Call<TopTracks> getTopTracks(
            @Query("method") String method,
            @Query("country") String country,
            @Query("api_key") String api_key,
            @Query("format") String format,
            @Query("page") String page
    );
}
