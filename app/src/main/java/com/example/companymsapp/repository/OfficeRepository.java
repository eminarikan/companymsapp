package com.example.companymsapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.companymsapp.apicall.OfficeApi;
import com.example.companymsapp.model.Office;
import com.example.companymsapp.tool.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfficeRepository {
    private static final OfficeRepository ourInstance = new OfficeRepository();
    private OfficeApi officeApi;

    private MutableLiveData<List<Office>> officesListLiveData = new MutableLiveData<>();
    private MutableLiveData<Office> officeLiveData = new MutableLiveData<>();

    public static OfficeRepository getInstance(){
        return ourInstance;
    }

    private OfficeRepository() {
        officeApi = RetrofitClientInstance.getRetrofitInstance().create(OfficeApi.class);
    }

    public MutableLiveData<List<Office>> getAll(){
        officeApi.getAll().enqueue(new Callback<List<Office>>() {
            @Override
            public void onResponse(Call<List<Office>> call, Response<List<Office>> response) {
                officesListLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Office>> call, Throwable t) {
                Log.d("Office Repository", "İSTEK BAŞARISIZ");
            }
        });
        return officesListLiveData;
    }

    public MutableLiveData<Office> getById(int id){
        officeApi.getById(id).enqueue(new Callback<Office>() {
            @Override
            public void onResponse(Call<Office> call, Response<Office> response) {
                officeLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Office> call, Throwable t) {

            }
        });
        return officeLiveData;
    }
}
