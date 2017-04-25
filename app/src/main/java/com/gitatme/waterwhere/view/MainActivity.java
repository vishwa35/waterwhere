package com.gitatme.waterwhere.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import com.gitatme.waterwhere.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button editProfileButton = (Button) findViewById(R.id.editProfileButton);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });
        Button newWaterReportButton = (Button) findViewById(R.id.newWaterReportButton);
        newWaterReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWaterReport();
            }
        });
        Button showAvailableWaterButton = (Button) findViewById(R.id.showAvailableWaterButton);
        showAvailableWaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAvailableWater();
            }
        });
        Button showAllReportsButton = (Button) findViewById(R.id.showAllReportsButton);
        showAllReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAllReports();
            }
        });
        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
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
    private void editProfile() {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        final int result = 1;
        startActivityForResult(profileIntent, result);
    }

    /**
     * Starts activity to create a new water report.
     */
    private void createWaterReport() {
        Intent reportIntent = new Intent(this, WaterReportActivity.class);
        final int result = 2;
        startActivityForResult(reportIntent, result);
    }

    /**
     * Starts activity to show available water sources.
     */
    private void showAvailableWater() {
        Intent waterAvailabilityIntent = new Intent(this, WaterAvailabilityActivity.class);
        final int result = 3;
        startActivityForResult(waterAvailabilityIntent, result);
    }

    /**
     * Starts activity to show past water reports.
     */
    private void viewAllReports() {

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
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if ("Success".equals(data.getStringExtra("Result"))) {
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
