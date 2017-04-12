package com.gitatme.waterwhere.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import com.gitatme.waterwhere.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import android.preference.PreferenceManager;
import com.gitatme.waterwhere.model.WaterReport;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button editProfileButton;
    Button newWaterReportButton;
    Button showAvailableWaterButton;
    Button showAllReportsButton;
    Button logoutButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editProfileButton = (Button) findViewById(R.id.editProfileButton);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });
        newWaterReportButton = (Button) findViewById(R.id.newWaterReportButton);
        newWaterReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWaterReport();
            }
        });
        showAvailableWaterButton = (Button) findViewById(R.id.showAvailableWaterButton);
        showAvailableWaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAvailableWater();
            }
        });
        showAllReportsButton = (Button) findViewById(R.id.showAllReportsButton);
        showAllReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAllReports();
            }
        });
        logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        //Check if user is logged in, and if not, send them to Onboarding
//        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_code), Context.MODE_PRIVATE);
//        boolean isLoggedIn = sharedPreferences.getBoolean(getString(R.string.shared_pref_loggedin), false);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null) {
            Intent intent = new Intent(this, OnboardingActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Starts a new activity for the user to change the profile information
     */
    public void editProfile() {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        final int result = 1;
        startActivityForResult(profileIntent, result);
    }

    /**
     * Starts activity to create a new water report.
     */
    public void createWaterReport() {
        Intent reportIntent = new Intent(this, WaterReportActivity.class);
        final int result = 2;
        startActivityForResult(reportIntent, result);
    }

    /**
     * Starts activity to show available water sources.
     */
    public void showAvailableWater() {
        Intent waterAvailabilityIntent = new Intent(this, WaterAvailabilityActivity.class);
        final int result = 3;
        startActivityForResult(waterAvailabilityIntent, result);
    }

    /**
     * Starts activity to show past water reports.
     */
    public void viewAllReports() {

        Intent waterReportViewActivity = new Intent(this, ViewWaterReportActivity.class);
        startActivity(waterReportViewActivity);

//        Set<String> strSet = sharedPreferences.getStringSet("waterReport", new HashSet<String>());
//        for (String json : strSet) {
//            WaterReport obj = gson.fromJson(json, WaterReport.class);
//            Toast.makeText(this, obj.toString(), Toast.LENGTH_SHORT).show();
//        }
    }

    /**
     * Logs out the user from the system and sets the boolean flag to false
     */
    public void logout() {
//        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_code), Context.MODE_PRIVATE);
//        sharedPreferences.edit().putBoolean(getString(R.string.shared_pref_loggedin), false).apply();
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data.getStringExtra("Result").equals("Success")) {
                Toast.makeText(this, "Changes were successfully made to your profile",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Changes were discarded",
                        Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 2) {
            // if water report added, make a success toast
            // if canceled, notify user through toast
        }

    }
}
