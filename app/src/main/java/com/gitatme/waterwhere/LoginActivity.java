package com.gitatme.waterwhere;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private final String USERNAME = "user"; //hardcoded for now
    private final String PASSWORD = "pass"; //hardcoded for now

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    }

    public void onClickLogin(View view) {
        if (usernameEditText.getText().toString().trim().isEmpty()
                && passwordEditText.getText().toString().isEmpty())  {
            Toast.makeText(this, "You did not enter a username or password",
                    Toast.LENGTH_SHORT).show();
        } else if (usernameEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "You did not enter a username",
                    Toast.LENGTH_SHORT).show();
        } else if (passwordEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "You did not enter a password",
                    Toast.LENGTH_SHORT).show();
        } else {
            if (usernameEditText.getText().toString().equals(USERNAME)
                    && passwordEditText.getText().toString().equals(PASSWORD)) {
                //User will be logged in
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_code), Context.MODE_PRIVATE);
                sharedPreferences.edit().putBoolean(getString(R.string.shared_pref_loggedin), true).apply();
                Intent loginSuccessful = new Intent(this,
                        MainActivity.class);
                startActivity(loginSuccessful);
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("Invalid Credentials")
                        .setMessage("The username or password you entered was incorrect")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        }
    }

    public void onCancelClick(View view) {
        //if cancel is pressed, simply go back to the onboarding screen
    }
}
