package com.example.companymsapp.activity.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.companymsapp.R;
import com.example.companymsapp.model.Product;
import com.example.companymsapp.viewmodel.ProductViewModel;

public class ProductDetailActivity extends AppCompatActivity {

    EditText productCodeUpdateTV,productNameUpdateTV,productLineUpdateTV,productScaleUpdateTV,
            productVendorUpdateTV,productDescriptionUpdateTV,quantityInStockUpdateTV,
            buyPriceUpdateTV,msrpUpdateTV;
    Button updateProductButton, deleteProductButton;

    ProductViewModel productViewModel;
    Product product;
    String productCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        init();

        updateProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productCode = productCodeUpdateTV.getText().toString();
                String productName = productNameUpdateTV.getText().toString();
                String productLine = productLineUpdateTV.getText().toString();
                String productScale = productScaleUpdateTV.getText().toString();
                String productVendor = productVendorUpdateTV.getText().toString();
                String productDescription = productDescriptionUpdateTV.getText().toString();
                String quantityInStock = quantityInStockUpdateTV.getText().toString();
                String buyPrice = buyPriceUpdateTV.getText().toString();
                String msrp = msrpUpdateTV.getText().toString();

                if(checkEmpty(productCode, productCodeUpdateTV, "Product Code")) return;
                if(checkEmpty(productName, productNameUpdateTV, "Product Name")) return;
                if(checkEmpty(productLine, productLineUpdateTV, "Product Line")) return;
                if(checkEmpty(productScale, productScaleUpdateTV, "Product Scale")) return;
                if(checkEmpty(productVendor, productVendorUpdateTV, "Product Vendor")) return;
                if(checkEmpty(productDescription, productDescriptionUpdateTV, "Product Description")) return;
                if(checkEmpty(quantityInStock, quantityInStockUpdateTV, "Quantity In Stock")) return;
                if(checkEmpty(buyPrice, buyPriceUpdateTV, "Buy Price")) return;
                if(checkEmpty(msrp, msrpUpdateTV, "MSRP")) return;

                Product p = new Product(
                        productCode,
                        productName,
                        productLine,
                        productScale,
                        productVendor,
                        productDescription,
                        Integer.valueOf(quantityInStock),
                        Double.valueOf(buyPrice),
                        Double.valueOf(msrp)
                );

                productViewModel.createProduct(p);
                finish();
            }
        });

        deleteProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productViewModel.deleteProduct(ProductDetailActivity.this.productCode);
                finish();
            }
        });
    }
    private void init(){
        productCodeUpdateTV = findViewById(R.id.productCodeUpdateTV);
        productNameUpdateTV = findViewById(R.id.productNameUpdateTV);
        productLineUpdateTV = findViewById(R.id.productLineUpdateTV);
        productScaleUpdateTV = findViewById(R.id.productScaleUpdateTV);
        productVendorUpdateTV = findViewById(R.id.productVendorUpdateTV);
        productDescriptionUpdateTV = findViewById(R.id.productDescriptionUpdateTV);
        quantityInStockUpdateTV = findViewById(R.id.quantityInStockUpdateTV);
        buyPriceUpdateTV = findViewById(R.id.buyPriceUpdateTV);
        msrpUpdateTV = findViewById(R.id.msrpUpdateTV);
        updateProductButton = findViewById(R.id.updateProductButton);
        deleteProductButton = findViewById(R.id.deleteProductButton);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productCode = getIntent().getStringExtra("PRODUCT_CODE");

        productViewModel.getSelectedProduct(productCode).observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                ProductDetailActivity.this.product = product;
                setTexts();
            }
        });
    }
    private void setTexts(){
        productCodeUpdateTV.setText(product.getProductCode());
        productCodeUpdateTV.setFocusable(false);
        productNameUpdateTV.setText(product.getProductName());
        productLineUpdateTV.setText(product.getProductLine());
        productScaleUpdateTV.setText(product.getProductScale());
        productVendorUpdateTV.setText(product.getProductVendor());
        productDescriptionUpdateTV.setText(product.getProductDescription());
        quantityInStockUpdateTV.setText(""+product.getQuantityInStock());
        buyPriceUpdateTV.setText(String.valueOf(product.getBuyPrice()));
        msrpUpdateTV.setText(String.valueOf(product.getMsrp()));
    }
    private boolean checkEmpty(String input, EditText view, String name) {
        if(input.trim().isEmpty()){
            view.setError(name+" Required!");
            return true;
        }
        return false;
    }
}