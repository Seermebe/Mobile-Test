package com.eltiempo.mobiletest.util;

import com.eltiempo.mobiletest.model.TopArtists;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopArtistsClient {

    @GET("/2.0/")
    Call<TopArtists> getTopArtist(
            @Query("method") String method,
            @Query("country") String country,
            @Query("api_key") String api_key,
            @Query("format") String format,
            @Query("page") String page
    );
}
