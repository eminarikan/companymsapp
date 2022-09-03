package com.example.companymsapp.activity.office;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.companymsapp.R;
import com.example.companymsapp.activity.OfficeActivity;
import com.example.companymsapp.model.Office;
import com.example.companymsapp.viewmodel.OfficeViewModel;

public class OfficeDetailActivity extends AppCompatActivity {

    EditText officeCodeUpdateTV, cityUpdateTV,stateUpdateTV,countryUpdateTV,
            address1UpdateTV,address2UpdateTV,
            phoneUpdateTV,postalCodeUpdateTV,territoryUpdateTV;
    Button updateButton, deleteButton;

    OfficeViewModel officeViewModel;
    Office office;
    Long officeCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_detail);

        init();


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String officeCode = officeCodeUpdateTV.getText().toString();
                String city = cityUpdateTV.getText().toString();
                String state = stateUpdateTV.getText().toString();
                String country = countryUpdateTV.getText().toString();
                String address1 = address1UpdateTV.getText().toString();
                String address2 = address2UpdateTV.getText().toString();
                String phone = phoneUpdateTV.getText().toString();
                String postalCode = postalCodeUpdateTV.getText().toString();
                String territory = territoryUpdateTV.getText().toString();

                if(checkEmpty(officeCode, officeCodeUpdateTV, "Office Code")) return;
                if(checkEmpty(city, cityUpdateTV, "City")) return;
                if(checkEmpty(state, stateUpdateTV, "State"))return;
                if(checkEmpty(country, countryUpdateTV, "Country"))return;
                if(checkEmpty(address1, address1UpdateTV, "Address Line 1"))return;
                if(checkEmpty(address2, address2UpdateTV, "Address Line 2"))return;
                if(checkEmpty(phone, phoneUpdateTV, "Phone"))return;
                if(checkEmpty(postalCode, postalCodeUpdateTV, "Postal Code"))return;
                if(checkEmpty(territory, territoryUpdateTV, "Territory"))return;

                Office office = new Office(Long.valueOf(officeCode),
                        city, state, country, address1, address2,
                        phone, postalCode, territory);

                officeViewModel.createOffice(office);
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                officeViewModel.deleteOffice(office.getOfficeCode());
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

    private void setHints() {
        officeCodeUpdateTV.setText(office.getOfficeCode().toString());
        officeCodeUpdateTV.setFocusable(false);
        cityUpdateTV.setText(office.getCity());
        stateUpdateTV.setText(office.getState());
        countryUpdateTV.setText(office.getCountry());
        address1UpdateTV.setText(office.getAddressLine1());
        address2UpdateTV.setText(office.getAddressLine2());
        territoryUpdateTV.setText(office.getTerritory());
        phoneUpdateTV.setText(office.getPhone());
        postalCodeUpdateTV.setText(office.getPostalCode());
    }

    private void init(){
        officeCodeUpdateTV = findViewById(R.id.officeCodeUpdateTV);
        cityUpdateTV = findViewById(R.id.cityUpdateTV);
        stateUpdateTV = findViewById(R.id.stateUpdateTV);
        countryUpdateTV = findViewById(R.id.countryUpdateTV);
        address1UpdateTV = findViewById(R.id.address1UpdateTV);
        address2UpdateTV = findViewById(R.id.address2UpdateTV);
        phoneUpdateTV = findViewById(R.id.phoneUpdateTV);
        postalCodeUpdateTV = findViewById(R.id.postalCodeUpdateTV);
        territoryUpdateTV = findViewById(R.id.territoryUpdateTV);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        officeViewModel = new ViewModelProvider(this).get(OfficeViewModel.class);

        officeCode = getIntent().getLongExtra("OFFICE_CODE",0);

        officeViewModel.getSelectedOffice(officeCode).observe(this, new Observer<Office>() {
            @Override
            public void onChanged(Office office) {
                OfficeDetailActivity.this.office = office;
                setHints();
            }
        });
    }
}