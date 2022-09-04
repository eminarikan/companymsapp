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
import com.example.companymsapp.activity.productline.CreateProductLineActivity;
import com.example.companymsapp.activity.productline.ProductLineDetailActivity;
import com.example.companymsapp.adapter.ProductLineListAdapter;
import com.example.companymsapp.adapter.ProductListAdapter;
import com.example.companymsapp.model.Product;
import com.example.companymsapp.model.ProductLine;
import com.example.companymsapp.viewmodel.ProductLineViewModel;
import com.example.companymsapp.viewmodel.ProductViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProductLineActivity extends AppCompatActivity implements ProductLineListAdapter.ItemOnClickListener{

    RecyclerView productLineListRV;
    FloatingActionButton createProductLineFAB;

    ProductLineViewModel productLineViewModel;
    ProductLineListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_line);

        init();
    }

    private void init(){
        createProductLineFAB = findViewById(R.id.createProductLineFAB);
        productLineListRV = findViewById(R.id.productLineListRV);
        productLineListRV.setLayoutManager(new LinearLayoutManager(this));

        productLineViewModel = new ViewModelProvider(this).get(ProductLineViewModel.class);
        productLineViewModel.getProductLines().observe(this, new Observer<List<ProductLine>>() {
            @Override
            public void onChanged(List<ProductLine> productLines) {
                adapter = new ProductLineListAdapter(productLines, ProductLineActivity.this);
                productLineListRV.setAdapter(adapter);
            }
        });

        createProductLineFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProductLineActivity.this, CreateProductLineActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        productLineViewModel.getProductLines().observe(this, new Observer<List<ProductLine>>() {
            @Override
            public void onChanged(List<ProductLine> productLines) {
                adapter = new ProductLineListAdapter(productLines, ProductLineActivity.this);
                productLineListRV.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onClick(ProductLine productLine) {
        Intent i = new Intent(this, ProductLineDetailActivity.class);
        i.putExtra("PRODUCT_LINE", productLine.getProductLine());
        startActivity(i);
    }
}