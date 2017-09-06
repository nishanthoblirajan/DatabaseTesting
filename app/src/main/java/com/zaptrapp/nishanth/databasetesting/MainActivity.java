package com.zaptrapp.nishanth.databasetesting;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.zaptrapp.nishanth.databasetesting.Adapter.RecyclerAdapter;
import com.zaptrapp.nishanth.databasetesting.Database.DbHelperProvider;
import com.zaptrapp.nishanth.databasetesting.Database.DbHelperProviderClient;
import com.zaptrapp.nishanth.databasetesting.Model.Data;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText nameET;
    private EditText amountET;
    private EditText descriptionET;

    private void findViews() {
        nameET = (EditText) findViewById(R.id.nameET);
        amountET = (EditText) findViewById(R.id.amountET);
        descriptionET = (EditText) findViewById(R.id.descriptionET);
    }


    RecyclerView recycler_view;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        recycler_view = (RecyclerView) findViewById(R.id.recyclerView);


        // use this if you want the RecyclerView to look like a vertical list view
        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new RecyclerAdapter(this);

        recycler_view.setAdapter(adapter);
    }

    public void saveData(View view) {
        String name = nameET.getText().toString();
        double amount = Double.parseDouble(amountET.getText().toString());
        String description = descriptionET.getText().toString();
        Data data = new Data(name,amount,description);
        DbHelperProviderClient.addDetails(
                data.getName(),
                 data.getAmount(),
                data.getDescription(),
                this);
        adapter.notifyItemInserted(DbHelperProviderClient.getAllDetails(this).getCount());
        Log.d("Data",data.toString());
    }

    public void deleteData(View view) {
        DbHelperProviderClient.removeAllDetails(this);
        adapter.notifyDataSetChanged();
    }
}
