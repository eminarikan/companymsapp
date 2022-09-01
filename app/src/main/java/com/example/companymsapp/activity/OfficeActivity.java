package com.example.companymsapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.companymsapp.R;
import com.example.companymsapp.adapter.OfficeListAdapder;
import com.example.companymsapp.model.Office;
import com.example.companymsapp.viewmodel.OfficeViewModel;

import java.util.List;

public class OfficeActivity extends AppCompatActivity implements OfficeListAdapder.ItemOnClickListener {

    RecyclerView officeListRV;
    OfficeViewModel officeViewModel;
    OfficeListAdapder adapder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);

        officeListRV = findViewById(R.id.officesListRV);
        officeListRV.setLayoutManager(new LinearLayoutManager(this));

        officeViewModel = new ViewModelProvider(this).get(OfficeViewModel.class);

        officeViewModel.getOffices().observe(this, new Observer<List<Office>>() {
            @Override
            public void onChanged(List<Office> offices) {
                adapder = new OfficeListAdapder(offices, OfficeActivity.this);
                officeListRV.setAdapter(adapder);
            }
        });

    }

    @Override
    public void onClick(Office office) {
        Toast.makeText(this, ""+office.toString(), Toast.LENGTH_LONG).show();
    }

}