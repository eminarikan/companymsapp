package com.example.companymsapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.companymsapp.apicall.OfficeApi;
import com.example.companymsapp.apicall.ProductApi;
import com.example.companymsapp.model.Message;
import com.example.companymsapp.model.Office;
import com.example.companymsapp.model.Product;
import com.example.companymsapp.tool.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private static final ProductRepository ourInstance = new ProductRepository();
    private ProductApi productApi;

    private MutableLiveData<List<Product>> productsListLiveData = new MutableLiveData<>();
    private MutableLiveData<Product> productLiveData = new MutableLiveData<>();

    public static ProductRepository getInstance(){
        return ourInstance;
    }

    private ProductRepository() {
        productApi = RetrofitClientInstance.getRetrofitInstance().create(ProductApi.class);
    }

    public MutableLiveData<List<Product>> getAll(){
        productApi.getAll().enqueue(new Callback<List<Product>>() {


            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productsListLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
        return productsListLiveData;
    }

    public MutableLiveData<Product> getById(String id){
        productApi.getById(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                productLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
        return productLiveData;
    }

    public void save(Product product) {
        productApi.save(product).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }

    public void delete(String id) {
        productApi.delete(id).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }
}
