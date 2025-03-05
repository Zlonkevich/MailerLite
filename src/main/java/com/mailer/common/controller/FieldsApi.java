package com.mailer.common.controller;

import com.mailer.common.dto.CreateFieldDTO;
import com.mailer.common.dto.CreateFieldResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface FieldsApi {

    @POST("/api/v1/fields")
    Call<CreateFieldResponse> createField(@Body CreateFieldDTO requestBody);

    @GET("/api/v1/fields")
    Call<ResponseBody> getFields();

    @DELETE("/api/v1/fields/{id}")
    Call<ResponseBody> deleteField(@Path("id") int id);
}