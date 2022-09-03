package com.example.companymsapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.companymsapp.R;
import com.example.companymsapp.activity.office.CreateOfficeActivity;
import com.example.companymsapp.activity.office.OfficeDetailActivity;
import com.example.companymsapp.adapter.OfficeListAdapder;
import com.example.companymsapp.model.Office;
import com.example.companymsapp.viewmodel.OfficeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class OfficeActivity extends AppCompatActivity implements OfficeListAdapder.ItemOnClickListener {

    RecyclerView officeListRV;
    OfficeViewModel officeViewModel;
    OfficeListAdapder adapder;
    FloatingActionButton createOfficeFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);

        createOfficeFAB = findViewById(R.id.createOfficeFAB);



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

        createOfficeFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OfficeActivity.this, CreateOfficeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        officeViewModel.getOffices().observe(this, new Observer<List<Office>>() {
            @Override
            public void onChanged(List<Office> offices) {
                adapder = new OfficeListAdapder(offices, OfficeActivity.this);
                officeListRV.setAdapter(adapder);
            }
        });
        super.onResume();
    }

    @Override
    public void onClick(Office office) {
        Intent i = new Intent(this, OfficeDetailActivity.class);
        i.putExtra("OFFICE_CODE", office.getOfficeCode());
        startActivity(i);
    }

}