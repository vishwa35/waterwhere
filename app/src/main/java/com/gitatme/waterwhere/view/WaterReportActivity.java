package com.gitatme.waterwhere.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gitatme.waterwhere.R;
import com.gitatme.waterwhere.model.ReportList;
import com.gitatme.waterwhere.model.WaterReport;
import com.google.firebase.database.DatabaseError;
import com.google.gson.Gson;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

/**
 * Created by shuka on 3/8/2017.
 */

public class WaterReportActivity extends Activity {

    private TextView nameTextView;
    private TextView reportNumTextView;
    private EditText datetimeEditText;
    private EditText latitudeEditText;
    private EditText longitudeEditText;
    private Spinner waterTypeSpinner;
    private Spinner waterConditionSpinner;
    private static boolean success = false;

    public static boolean getSuc() {
        return success;
    }
    private Button createReportButton;

    FirebaseAuth mAuth;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        success = false;
        nameTextView = (TextView) findViewById(R.id.textName);
        reportNumTextView = (TextView) findViewById(R.id.textReportNumber);
        datetimeEditText = (EditText) findViewById(R.id.editTextDate);
        latitudeEditText = (EditText) findViewById(R.id.editTextLatitude);
        longitudeEditText = (EditText) findViewById(R.id.editTextLongitude);
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
        String name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        nameTextView.setText(name);
        Random r = new Random();
        reportNumTextView.setText(String.valueOf(Math.abs(name.hashCode())/(r.nextInt(10000))));

        dbRef = FirebaseDatabase.getInstance().getReference();
    }

    public void onClickCreateReport() {
        if ((nameTextView.getText().toString().trim().isEmpty())
                || (datetimeEditText.getText().toString().trim().isEmpty())
                || (latitudeEditText.getText().toString().trim().isEmpty())
                || (longitudeEditText.getText().toString().trim().isEmpty())) {
            new AlertDialog.Builder(this)
                    .setTitle("Missing Information")
                    .setMessage("One or more of the fields were left blank. Please enter the information completely.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            double latitude;
            double longitude;
            String latitudeString = latitudeEditText.getText().toString();
            String longitudeString = longitudeEditText.getText().toString();
            try {
                latitude = Double.parseDouble(latitudeString);
                longitude = Double.parseDouble(longitudeString);

                if (!((latitude <= 90.0 && latitude >= -90.0)
                        && (longitude <= 180.0 && longitude >= -180.0))) {
                    Toast.makeText(this, "Latitude and Longitude values are out of range " +
                            "(-90 <= latitude <= 90, -180 <= longitude <= 180)", Toast.LENGTH_SHORT).show();
                    success = false;
                } else {
                    final WaterReport waterReport =
                            new WaterReport(
                                    nameTextView.getText().toString(),
                                    reportNumTextView.getText().toString(),
                                    datetimeEditText.getText().toString(),
                                    latitude,
                                    longitude,
                                    waterTypeSpinner.getSelectedItem().toString(),
                                    waterConditionSpinner.getSelectedItem().toString());
                    success = true;

                    final Context context = this;

                    dbRef.child("waterReport").push().setValue(waterReport, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError != null) {
                                Toast.makeText(context, "Report failed to save", Toast.LENGTH_SHORT).show();;
                            } else {
                                Toast.makeText(context, "Report Added", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                    Intent mainActivity = new Intent(this, MainActivity.class);
                    startActivity(mainActivity);
                }


            } catch (Exception e1) {
                // this means it is not double
                Toast.makeText(this, "Latitude/Longitude values cannot be interpreted. " +
                                "Please try entering them again. Make sure to enter a decimal.",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void onClickCancel(View view) {
        //Aditya
        //Send back to Main Activity and notify via toast that changes were canceled
        Toast.makeText(this, "Report Canceled", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
