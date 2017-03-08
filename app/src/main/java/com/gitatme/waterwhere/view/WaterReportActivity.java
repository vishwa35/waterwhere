package com.gitatme.waterwhere.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gitatme.waterwhere.R;
import com.gitatme.waterwhere.model.WaterReport;

import java.util.Random;

/**
 * Created by shuka on 3/8/2017.
 */

public class WaterReportActivity extends Activity {

    private TextView nameTextView;
    private TextView reportNumTextView;
    private EditText datetimeEditText;
    private EditText locationEditText;
    private Spinner waterTypeSpinner;
    private Spinner waterConditionSpinner;

    private Button createReportButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        nameTextView = (TextView) findViewById(R.id.textName);
        reportNumTextView = (TextView) findViewById(R.id.textReportNumber);
        datetimeEditText = (EditText) findViewById(R.id.editTextDate);
        locationEditText = (EditText) findViewById(R.id.editTextLocation);
        waterTypeSpinner = (Spinner) findViewById(R.id.spinnerWaterType);
        waterConditionSpinner = (Spinner) findViewById(R.id.spinnerWaterConditions);
        createReportButton = (Button) findViewById(R.id.createReportButton);
        createReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreateReport();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_code), Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(getString(R.string.shared_pref_name), "");
        nameTextView.setText(name);
        Random r = new Random();
        reportNumTextView.setText(String.valueOf(Math.abs(name.hashCode())/(r.nextInt(10000))));
    }

    public void onClickCreateReport() {
        WaterReport waterReport =
                new WaterReport(
                        nameTextView.getText().toString(),
                        reportNumTextView.getText().toString(),
                        datetimeEditText.getText().toString(),
                        locationEditText.getText().toString(),
                        waterTypeSpinner.getSelectedItem().toString(),
                        waterConditionSpinner.getSelectedItem().toString());

        //vishwa - store waterReport in sharedpref
    }

    public void onClickCancel(View view) {
        //Aditya
        //Send back to Main Activity and notify via toast that changes were canceled
    }
}
