package com.example.companymsapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.companymsapp.model.Office;
import com.example.companymsapp.model.Product;
import com.example.companymsapp.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends ViewModel {
    private MutableLiveData<List<Product>> products;
    private MutableLiveData<Product> selectedProduct = new MutableLiveData<>();

    private ProductRepository productRepository = ProductRepository.getInstance();

    public ProductViewModel() {
        super();
        products = productRepository.getAll();
    }

    public MutableLiveData<List<Product>> getProducts() {
        return productRepository.getAll();
    }

    public MutableLiveData<Product> getSelectedProduct(String productCode) {
        return productRepository.getById(productCode);
    }

    public void createProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProduct(String id){
        productRepository.delete(id);
    }
}
