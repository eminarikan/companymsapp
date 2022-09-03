package com.example.companymsapp.apicall;

import com.example.companymsapp.model.Message;
import com.example.companymsapp.model.Office;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OfficeApi {
    @GET("offices/{id}")
    Call<Office> getById(@Path("id") Long id);

    @GET("offices")
    Call<List<Office>> getAll();

    @POST("offices")
    Call<Message> save(@Body Office office);

    @DELETE("offices/{id}")
    Call<Message> delete(@Path("id") Long id);
}
