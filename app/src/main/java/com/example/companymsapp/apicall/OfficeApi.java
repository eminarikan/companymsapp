package com.example.companymsapp.apicall;

import com.example.companymsapp.model.Office;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OfficeApi {
    @GET("offices/{id}")
    Call<Office> getById(@Path("id") int id);

    @GET("offices")
    Call<List<Office>> getAll();
}
