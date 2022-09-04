package com.example.companymsapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.companymsapp.model.ProductLine;
import com.example.companymsapp.repository.ProductLineRepository;
import com.example.companymsapp.repository.ProductRepository;

import java.util.List;

public class ProductLineViewModel extends ViewModel {
    private MutableLiveData<List<ProductLine>> productLines;
    private MutableLiveData<ProductLine> selectedProduct = new MutableLiveData<>();

    private ProductLineRepository productLineRepository = ProductLineRepository.getInstance();

    public ProductLineViewModel() {
        super();
        productLines = productLineRepository.getAll();
    }

    public MutableLiveData<List<ProductLine>> getProductLines() {
        return productLineRepository.getAll();
    }

    public MutableLiveData<ProductLine> getSelectedProductLine(String productLine) {
        return productLineRepository.getById(productLine);
    }

    public void createProductLine(ProductLine productLine){
        productLineRepository.save(productLine);
    }

    public void deleteProductLine(String id){
        productLineRepository.delete(id);
    }
}
