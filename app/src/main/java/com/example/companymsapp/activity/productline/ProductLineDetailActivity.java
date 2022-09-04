package com.example.companymsapp.activity.productline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.companymsapp.R;
import com.example.companymsapp.model.ProductLine;
import com.example.companymsapp.viewmodel.ProductLineViewModel;

public class ProductLineDetailActivity extends AppCompatActivity {

    EditText productLineUpdateTV,textDescriptionUpdateTV,htmlDescriptionUpdateTV,imageUpdateTV;
    Button updateProductLineButton, deleteProductLineButton;

    ProductLineViewModel productLineViewModel;
    ProductLine productLine;
    String productLineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_line_detail);

        init();

        updateProductLineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prodLn = productLineUpdateTV.getText().toString();
                String txtDesc = textDescriptionUpdateTV.getText().toString();
                String htmlDesc = htmlDescriptionUpdateTV.getText().toString();
                String img = imageUpdateTV.getText().toString();

                if(checkEmpty(prodLn, productLineUpdateTV, "Product Line"))return;
                if(checkEmpty(txtDesc, textDescriptionUpdateTV, "Text Description"))return;
                if(checkEmpty(htmlDesc, htmlDescriptionUpdateTV, "Html Description"))return;
                if(checkEmpty(img, imageUpdateTV, "Image"))return;

                ProductLine pl = new ProductLine(prodLn, txtDesc, htmlDesc, img);

                productLineViewModel.createProductLine(pl);
                finish();

            }
        });
        deleteProductLineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productLineViewModel.deleteProductLine(productLineId);
                finish();
            }
        });
    }

    private void init(){
        productLineUpdateTV = findViewById(R.id.productLineUpdateTV);
        textDescriptionUpdateTV = findViewById(R.id.textDescriptionUpdateTV);
        htmlDescriptionUpdateTV = findViewById(R.id.htmlDescriptionUpdateTV);
        imageUpdateTV = findViewById(R.id.imageUpdateTV);
        updateProductLineButton = findViewById(R.id.updateProductLineButton);
        deleteProductLineButton = findViewById(R.id.deleteProductLineButton);

        productLineViewModel = new ViewModelProvider(this).get(ProductLineViewModel.class);

        productLineId = getIntent().getStringExtra("PRODUCT_LINE");
        productLineViewModel.getSelectedProductLine(productLineId).observe(this, new Observer<ProductLine>() {
            @Override
            public void onChanged(ProductLine productLine) {
                ProductLineDetailActivity.this.productLine = productLine;
                setTexts();
            }
        });
    }

    private void setTexts(){
        productLineUpdateTV.setText(productLine.getProductLine());
        productLineUpdateTV.setFocusable(false);
        textDescriptionUpdateTV.setText(productLine.getTextDescription());
        htmlDescriptionUpdateTV.setText(productLine.getHtmlDescription());
        imageUpdateTV.setText(productLine.getImage());
    }

    private boolean checkEmpty(String input, EditText view, String name) {
        if(input.trim().isEmpty()){
            view.setError(name+" Required!");
            return true;
        }
        return false;
    }
}