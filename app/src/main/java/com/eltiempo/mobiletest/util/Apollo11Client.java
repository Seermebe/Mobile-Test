package com.eltiempo.mobiletest.util;

import com.eltiempo.mobiletest.model.Apollo11;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apollo11Client {

    @GET("/search")
    Call<Apollo11> getApollo11(
            @Query("q") String q
    );
}
