package com.gitatme.waterwhere;

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
            //finish();
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

        if (data.getStringExtra("Result").equals("Success")) {
            Toast.makeText(this, "Changes were successfully made to your profile",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Changes were discarded",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
