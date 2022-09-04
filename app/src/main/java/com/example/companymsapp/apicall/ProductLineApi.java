package com.example.companymsapp.apicall;

import com.example.companymsapp.model.Message;
import com.example.companymsapp.model.ProductLine;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductLineApi {
    @GET("productlines/{id}")
    Call<ProductLine> getById(@Path("id") String id);

    @GET("productlines")
    Call<List<ProductLine>> getAll();

    @POST("productlines")
    Call<Message> save(@Body ProductLine productLine);

    @DELETE("productlines/{id}")
    Call<Message> delete(@Path("id") String id);
}
