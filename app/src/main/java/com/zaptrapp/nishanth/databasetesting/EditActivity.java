package com.zaptrapp.nishanth.databasetesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zaptrapp.nishanth.databasetesting.Database.DbHelperProviderClient;

public class EditActivity extends AppCompatActivity {
    private EditText mName_edit;
    private EditText mAmount_edit;
    private EditText mDescription_edit;
    private Button mEdit_button_editActivity;

    private void bindViews() {

        mName_edit = (EditText) findViewById(R.id.name_edit);
        mAmount_edit = (EditText) findViewById(R.id.amount_edit);
        mDescription_edit = (EditText) findViewById(R.id.description_edit);
        mEdit_button_editActivity = (Button) findViewById(R.id.edit_button_editActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        bindViews();

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            id = bundle.getInt("id");
            name = bundle.getString("name");
            amount = bundle.getDouble("amount");
            description = bundle.getString("description");

            Log.d("TDDA",id+" "+name+" "+amount+" "+description);
            mName_edit.setText(name);
            mAmount_edit.setText(String.valueOf(amount));
            mDescription_edit.setText(description);
        }

    }
        int id;
        String name;
        double amount;
        String description;

    public void editData(View view) {


        DbHelperProviderClient.updateDetails(id,
                mName_edit.getText().toString(),
                Double.parseDouble(mAmount_edit.getText().toString()),
                mDescription_edit.getText().toString(),this);

        if(id == 34){
            UpdateNumberService.startActionUpdateWidget(this);
        }
        finish();

    }
}
