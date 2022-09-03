package com.example.companymsapp.activity.office;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.companymsapp.R;
import com.example.companymsapp.model.Office;
import com.example.companymsapp.viewmodel.OfficeViewModel;

public class CreateOfficeActivity extends AppCompatActivity {

    EditText officeCodeTV, cityTV,stateTV,countryTV,address1TV,address2TV,
    phoneTV,postalCodeTV,territoryTV;
    Button saveButton;

    OfficeViewModel officeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_office);

        init();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String officeCode = officeCodeTV.getText().toString();
                String city = cityTV.getText().toString();
                String state = stateTV.getText().toString();
                String country = countryTV.getText().toString();
                String address1 = address1TV.getText().toString();
                String address2 = address2TV.getText().toString();
                String phone = phoneTV.getText().toString();
                String postalCode = postalCodeTV.getText().toString();
                String territory = territoryTV.getText().toString();

                if(checkEmpty(officeCode, officeCodeTV, "Office Code")) return;
                if(checkEmpty(city, cityTV, "City")) return;
                if(checkEmpty(state, stateTV, "State"))return;
                if(checkEmpty(country, countryTV, "Country"))return;
                if(checkEmpty(address1, address1TV, "Address Line 1"))return;
                if(checkEmpty(address2, address2TV, "Address Line 2"))return;
                if(checkEmpty(phone, phoneTV, "Phone"))return;
                if(checkEmpty(postalCode, postalCodeTV, "Postal Code"))return;
                if(checkEmpty(territory, territoryTV, "Territory"))return;

                Office office = new Office(Long.valueOf(officeCode),
                        city, state, country, address1, address2,
                        phone, postalCode, territory);

                officeViewModel.createOffice(office);
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
        officeCodeTV = findViewById(R.id.officeCodeUpdateTV);
        cityTV = findViewById(R.id.cityUpdateTV);
        stateTV = findViewById(R.id.stateUpdateTV);
        countryTV = findViewById(R.id.countryUpdateTV);
        address1TV = findViewById(R.id.address1UpdateTV);
        address2TV = findViewById(R.id.address2UpdateTV);
        phoneTV = findViewById(R.id.phoneUpdateTV);
        postalCodeTV = findViewById(R.id.postalCodeUpdateTV);
        territoryTV = findViewById(R.id.territoryUpdateTV);
        saveButton = findViewById(R.id.updateButton);

        officeViewModel = new ViewModelProvider(this).get(OfficeViewModel.class);
    }
}