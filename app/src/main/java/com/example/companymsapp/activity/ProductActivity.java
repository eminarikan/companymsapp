package com.example.companymsapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.companymsapp.R;
import com.example.companymsapp.adapter.ProductListAdapter;
import com.example.companymsapp.model.Product;
import com.example.companymsapp.viewmodel.ProductViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProductActivity extends AppCompatActivity implements ProductListAdapter.ItemOnClickListener {

    RecyclerView productListRV;
    FloatingActionButton createProductFAB;

    ProductViewModel productViewModel;
    ProductListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        init();

    }
    private void init(){
        createProductFAB = findViewById(R.id.createProductFAB);
        productListRV = findViewById(R.id.productListRV);
        productListRV.setLayoutManager(new LinearLayoutManager(this));

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter = new ProductListAdapter(products, ProductActivity.this);
                productListRV.setAdapter(adapter);
            }
        });

    }

    @Override
    public void onClick(Product product) {

    }
}