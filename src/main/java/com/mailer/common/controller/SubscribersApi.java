package com.mailer.common.controller;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SubscribersApi {

    @GET("/api/v1/subscribers")
    Call<ResponseBody> getFields();
}
