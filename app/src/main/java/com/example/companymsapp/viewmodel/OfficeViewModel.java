package com.example.companymsapp.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.companymsapp.model.Office;
import com.example.companymsapp.repository.OfficeRepository;

import java.util.List;


public class OfficeViewModel extends ViewModel {
    // LiveData MutableLiveData
    private MutableLiveData<List<Office>> offices;
    private MutableLiveData<Office> selectedOffice = new MutableLiveData<>();

    private OfficeRepository officeRepository = OfficeRepository.getInstance();

    public  OfficeViewModel(){
        super();
        offices = officeRepository.getAll();
    }

    public MutableLiveData<Office> getSelectedOffice(Long officeCode) {
        return officeRepository.getById(officeCode);
    }

    public MutableLiveData<List<Office>> getOffices(){
        return officeRepository.getAll();
    }

    public void createOffice(Office office){
        officeRepository.save(office);
    }

    public void deleteOffice(Long id){
        officeRepository.delete(id);
    }
}
