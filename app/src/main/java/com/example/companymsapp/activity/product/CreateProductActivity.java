package com.example.companymsapp.activity.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.companymsapp.R;
import com.example.companymsapp.model.Product;
import com.example.companymsapp.viewmodel.ProductViewModel;

public class CreateProductActivity extends AppCompatActivity {

    EditText productCodeTV,productNameTV,productLineTV,productScaleTV,
            productVendorTV,productDescriptionTV,quantityInStockTV,
            buyPriceTV,msrpTV;
    Button saveProductButton;

    ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        init();

        saveProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productCode = productCodeTV.getText().toString();
                String productName = productNameTV.getText().toString();
                String productLine = productLineTV.getText().toString();
                String productScale = productScaleTV.getText().toString();
                String productVendor = productVendorTV.getText().toString();
                String productDescription = productDescriptionTV.getText().toString();
                String quantityInStock = quantityInStockTV.getText().toString();
                String buyPrice = buyPriceTV.getText().toString();
                String msrp = msrpTV.getText().toString();

                if(checkEmpty(productCode, productCodeTV, "Product Code")) return;
                if(checkEmpty(productName, productNameTV, "Product Name")) return;
                if(checkEmpty(productLine, productLineTV, "Product Line")) return;
                if(checkEmpty(productScale, productScaleTV, "Product Scale")) return;
                if(checkEmpty(productVendor, productVendorTV, "Product Vendor")) return;
                if(checkEmpty(productDescription, productDescriptionTV, "Product Description")) return;
                if(checkEmpty(quantityInStock, quantityInStockTV, "Quantity In Stock")) return;
                if(checkEmpty(buyPrice, buyPriceTV, "Buy Price")) return;
                if(checkEmpty(msrp, msrpTV, "MSRP")) return;

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
    }

    private boolean checkEmpty(String input, EditText view, String name) {
        if(input.trim().isEmpty()){
            view.setError(name+" Required!");
            return true;
        }
        return false;
    }

    private void init(){
        productCodeTV = findViewById(R.id.productCodeTV);
        productNameTV = findViewById(R.id.productNameTV);
        productLineTV = findViewById(R.id.productLineTV);
        productScaleTV = findViewById(R.id.productScaleTV);
        productVendorTV = findViewById(R.id.productVendorTV);
        productDescriptionTV = findViewById(R.id.productDescriptionTV);
        quantityInStockTV = findViewById(R.id.quantityInStockTV);
        buyPriceTV = findViewById(R.id.buyPriceTV);
        msrpTV = findViewById(R.id.msrpTV);
        saveProductButton = findViewById(R.id.saveProductButton);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

    }

}