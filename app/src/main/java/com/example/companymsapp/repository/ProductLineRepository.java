package com.example.companymsapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.companymsapp.apicall.ProductLineApi;
import com.example.companymsapp.model.Message;
import com.example.companymsapp.model.ProductLine;
import com.example.companymsapp.tool.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductLineRepository {
    private static final ProductLineRepository ourInstance = new ProductLineRepository();
    private ProductLineApi productLineApi;

    private MutableLiveData<List<ProductLine>> productsListLiveData = new MutableLiveData<>();
    private MutableLiveData<ProductLine> productLiveData = new MutableLiveData<>();

    public static ProductLineRepository getInstance(){
        return ourInstance;
    }

    private ProductLineRepository() {
        productLineApi = RetrofitClientInstance.getRetrofitInstance().create(ProductLineApi.class);
    }

    public MutableLiveData<List<ProductLine>> getAll(){
        productLineApi.getAll().enqueue(new Callback<List<ProductLine>>() {


            @Override
            public void onResponse(Call<List<ProductLine>> call, Response<List<ProductLine>> response) {
                productsListLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductLine>> call, Throwable t) {

            }
        });
        return productsListLiveData;
    }

    public MutableLiveData<ProductLine> getById(String id){
        productLineApi.getById(id).enqueue(new Callback<ProductLine>() {
            @Override
            public void onResponse(Call<ProductLine> call, Response<ProductLine> response) {
                productLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductLine> call, Throwable t) {

            }
        });
        return productLiveData;
    }

    public void save(ProductLine productLine) {
        productLineApi.save(productLine).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }

    public void delete(String id) {
        productLineApi.delete(id).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }
}
