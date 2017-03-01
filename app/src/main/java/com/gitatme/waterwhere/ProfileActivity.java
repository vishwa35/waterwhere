package com.gitatme.waterwhere;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by shuka on 3/1/2017.
 */

public class ProfileActivity extends Activity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passEditText;
    private EditText confirmPassEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    private Spinner accountTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nameEditText = (EditText) findViewById(R.id.editTextName);
        emailEditText = (EditText) findViewById(R.id.editTextEmail);
        passEditText = (EditText) findViewById(R.id.editTextPass);
        confirmPassEditText = (EditText) findViewById(R.id.editTextConfirmPass);
        addressEditText = (EditText) findViewById(R.id.editTextAddress);
        phoneEditText = (EditText) findViewById(R.id.editTextPhone);
        accountTypeSpinner = (Spinner) findViewById(R.id.spinnerAccountType);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_code), Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(getString(R.string.shared_pref_name), null);
        String email = sharedPreferences.getString(getString(R.string.shared_pref_email), null);
        String pass = sharedPreferences.getString(getString(R.string.shared_pref_pass), null);
        String address = sharedPreferences.getString(getString(R.string.shared_pref_address), null);
        String phone = sharedPreferences.getString(getString(R.string.shared_pref_phone), null);
        String accountType = sharedPreferences.getString(getString(R.string.shared_pref_type), null);

        if (name != null) {
            nameEditText.setText(name);
        }

        if (email != null) {
            emailEditText.setText(email);
        }
        if (pass != null) {
            passEditText.setText(pass);
            confirmPassEditText.setText(pass);
        }
        if (address != null) {
            addressEditText.setText(address);
        }
        if (phone != null) {
            phoneEditText.setText(phone);
        }


        if (accountType.toLowerCase().trim().equals("user")) {
            accountTypeSpinner.setSelection(0);
        } else if (accountType.toLowerCase().trim().equals("worker")) {
            accountTypeSpinner.setSelection(1);
        } else if (accountType.toLowerCase().trim().equals("manager")) {
            accountTypeSpinner.setSelection(2);
        } else if (accountType.toLowerCase().trim().equals("admin")) {
            accountTypeSpinner.setSelection(3);
        }


        setContentView(R.layout.activity_profile);
    }

    /**
     * The changes are discarded if cancel is clicked and an intent is called
     * to go back to the main activity
     *
     * @param view
     */
    public void onClickCancel(View view) {
        Intent goingBack = new Intent();
        goingBack.putExtra("Result", "Cancel");
        setResult(RESULT_OK, goingBack);
        finish();
    }

    /**
     * If the user clicks submit changes, this method is called. It makes sure
     * the text fields and spinner have valid inputs and, if so, these changes are
     * committed and the sharedpreferences are updated.
     *
     * @param view
     */
    public void onClickSubmit(View view) {
        if ((nameEditText.getText().toString().trim().isEmpty())
            || (emailEditText.getText().toString().trim().isEmpty())
                || (passEditText.getText().toString().trim().isEmpty())
                || (addressEditText.getText().toString().trim().isEmpty())
                || (phoneEditText.getText().toString().trim().isEmpty())
                || (confirmPassEditText.getText().toString().trim().isEmpty())) {
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
        } else if (!passEditText.getText().toString().equals(confirmPassEditText.getText().toString())) {
            new AlertDialog.Builder(this)
                    .setTitle("Passwords do not match")
                    .setMessage("The two passwords do not match. Please enter the information again.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            SharedPreferences.Editor sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_code), Context.MODE_PRIVATE).edit();
            sharedPreferences.putString(getString(R.string.shared_pref_name), nameEditText.getText().toString());
            sharedPreferences.putString(getString(R.string.shared_pref_email), emailEditText.getText().toString());
            sharedPreferences.putString(getString(R.string.shared_pref_pass), passEditText.getText().toString());
            sharedPreferences.putString(getString(R.string.shared_pref_address), addressEditText.getText().toString());
            sharedPreferences.putString(getString(R.string.shared_pref_phone), phoneEditText.getText().toString());
            sharedPreferences.putString(getString(R.string.shared_pref_type), accountTypeSpinner.getSelectedItem().toString());

            sharedPreferences.commit();

            //TODO - add option for user to back out even after they click submit changes

            Intent goingBack = new Intent();
            goingBack.putExtra("Result", "Success");
            setResult(RESULT_OK, goingBack);
            finish();

        }
    }
}
