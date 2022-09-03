package com.example.companymsapp.apicall;

import com.example.companymsapp.model.Message;
import com.example.companymsapp.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductApi {
    @GET("products/{id}")
    Call<Product> getById(@Path("id") String id);

    @GET("products")
    Call<List<Product>> getAll();

    @POST("products")
    Call<Message> save(@Body Product product);

    @DELETE("products/{id}")
    Call<Message> delete(@Path("id") String id);
}
