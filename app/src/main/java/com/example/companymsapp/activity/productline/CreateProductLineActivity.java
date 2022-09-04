package com.example.companymsapp.activity.productline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.companymsapp.R;
import com.example.companymsapp.model.ProductLine;
import com.example.companymsapp.viewmodel.ProductLineViewModel;
import com.example.companymsapp.viewmodel.ProductViewModel;

public class CreateProductLineActivity extends AppCompatActivity {

    EditText productLineTV,textDescriptionTV,htmlDescriptionTV,imageTV;
    Button saveProductLineButton;

    ProductLineViewModel productLineViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product_line);

        init();

        saveProductLineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productLine = productLineTV.getText().toString();
                String textDescription = textDescriptionTV.getText().toString();
                String htmlDescription = htmlDescriptionTV.getText().toString();
                String image = imageTV.getText().toString();

                if (checkEmpty(productLine, productLineTV, "Product Line")) return;
                if (checkEmpty(textDescription, textDescriptionTV, "Text Description")) return;
                if (checkEmpty(htmlDescription, htmlDescriptionTV, "Html Description")) return;
                if (checkEmpty(image, imageTV, "Image")) return;

                ProductLine pl = new ProductLine(productLine, textDescription, htmlDescription, image);

                productLineViewModel.createProductLine(pl);

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
        productLineTV = findViewById(R.id.productLineTV);
        textDescriptionTV = findViewById(R.id.textDescriptionTV);
        htmlDescriptionTV = findViewById(R.id.htmlDescriptionTV);
        imageTV = findViewById(R.id.imageTV);
        saveProductLineButton = findViewById(R.id.saveProductLineButton);

        productLineViewModel = new ViewModelProvider(this).get(ProductLineViewModel.class);

    }
}