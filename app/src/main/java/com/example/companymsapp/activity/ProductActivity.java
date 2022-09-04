package com.example.companymsapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.companymsapp.R;
import com.example.companymsapp.activity.product.CreateProductActivity;
import com.example.companymsapp.activity.product.ProductDetailActivity;
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

        createProductFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProductActivity.this, CreateProductActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        Intent i = new Intent(this, ProductDetailActivity.class);
        i.putExtra("PRODUCT_CODE", product.getProductCode());
        startActivity(i);
    }
}