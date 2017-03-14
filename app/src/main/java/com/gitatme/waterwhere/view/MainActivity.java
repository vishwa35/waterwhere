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
import android.widget.Toast;
import android.widget.TextView;

import com.gitatme.waterwhere.R;
import com.google.gson.Gson;
import android.preference.PreferenceManager;
import com.gitatme.waterwhere.model.WaterReport;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Check if user is logged in, and if not, send them to Onboarding
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_code), Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean(getString(R.string.shared_pref_loggedin), false);
        if(!isLoggedIn) {
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
     * Logs out the user from the system and sets the boolean flag to false
     *
     * @param view
     */
    public void logout(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_code), Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(getString(R.string.shared_pref_loggedin), false).apply();
        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickReport(View view) {
        Intent reportIntent = new Intent(this, WaterReportActivity.class);
        final int result = 2;
        startActivityForResult(reportIntent, result);
    }

    public void onClickViewReport(View view) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String json1 = sharedPreferences.getString("waterReport", "");
        WaterReport obj1 = gson.fromJson(json1, WaterReport.class);
//        Toast.makeText(this, obj1.toString(), Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_view_water_report);
        TextView reportText = (TextView)findViewById(R.id.report);
        reportText.setText(obj1.toString());

//        Intent waterReportViewActivity = new Intent(this, ViewWaterReportActivity.class);
//        startActivity(waterReportViewActivity);

//        Set<String> strSet = sharedPreferences.getStringSet("waterReport", new HashSet<String>());
//        for (String json : strSet) {
//            WaterReport obj = gson.fromJson(json, WaterReport.class);
//            Toast.makeText(this, obj.toString(), Toast.LENGTH_SHORT).show();
//        }
    }

    /**
     * Starts a new activity for the user to change the profile information
     *
     * @param view
     */
    public void onClickEditProfile(View view) {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        final int result = 1;
        startActivityForResult(profileIntent, result);
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
