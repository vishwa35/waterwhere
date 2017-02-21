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

    public void logout(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_code), Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(getString(R.string.shared_pref_loggedin), false).apply();
        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);
        finish();
    }
}
