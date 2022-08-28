package com.example.companymsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.companymsapp.model.Office;
import com.example.companymsapp.repository.OfficeRepository;

import java.util.List;


public class OfficeViewModel extends ViewModel {
    // LiveData MutableLiveData
    private MutableLiveData<List<Office>> offices;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private OfficeRepository officeRepository = OfficeRepository.getInstance();

    public  OfficeViewModel(){
        super();
        isLoading.setValue(true);
        offices = officeRepository.getAll();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<List<Office>> getOffices(){
        return offices;
    }


}
