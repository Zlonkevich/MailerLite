package com.mailer.common.controller;

import com.mailer.common.dto.subscribers.GetSubscribersRec;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SubscribersApi {

    @GET("/api/v1/subscribers")
    Call<GetSubscribersRec> getFields();
}
