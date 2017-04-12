package com.gitatme.waterwhere.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;import android.widget.EditText;
import android.widget.Spinner;
import com.gitatme.waterwhere.R;
import com.gitatme.waterwhere.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends Activity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passEditText;
    private EditText confirmPassEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    private Spinner accountTypeSpinner;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameEditText = (EditText) findViewById(R.id.editTextName);
        emailEditText = (EditText) findViewById(R.id.editTextEmail);
        passEditText = (EditText) findViewById(R.id.editTextPass);
        confirmPassEditText = (EditText) findViewById(R.id.editTextConfirmPass);
        addressEditText = (EditText) findViewById(R.id.editTextAddress);
        phoneEditText = (EditText) findViewById(R.id.editTextPhone);
        accountTypeSpinner = (Spinner) findViewById(R.id.spinnerAccountType);

        //Firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };
        final FirebaseUser firebaseUser = mAuth.getCurrentUser();
        DatabaseReference userRef = mDatabase.child("users").child(firebaseUser.getUid());
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                Log.d("SHIT", dataSnapshot.toString());

                String name = dataSnapshot.child("name").getValue().toString();
                String email = firebaseUser.getEmail();
                String address = dataSnapshot.child("address").getValue().toString();
                String phone = dataSnapshot.child("phone").getValue().toString();
                String accountType = dataSnapshot.child("type").getValue().toString();

                nameEditText.setText(name);
                emailEditText.setText(email);
                addressEditText.setText(address);
                phoneEditText.setText(phone);

                if ("user".equals(accountType.toLowerCase().trim())) {
                    accountTypeSpinner.setSelection(0);
                } else if ("worker".equals(accountType.toLowerCase().trim())) {
                    accountTypeSpinner.setSelection(1);
                } else if ("manager".equals(accountType.toLowerCase().trim())) {
                    accountTypeSpinner.setSelection(2);
                } else if ("admin".equals(accountType.toLowerCase().trim())) {
                    accountTypeSpinner.setSelection(3);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("SHIT", "Failed");
            }
        });
    }

    /**
     * The changes are discarded if cancel is clicked and an intent is called
     * to go back to the main activity
     *
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
                    .setMessage(
                            "One or more of the fields were left blank. " +
                                    "Please enter the information completely.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else if (!passEditText.getText().toString()
                .equals(confirmPassEditText.getText().toString())) {
            new AlertDialog.Builder(this)
                    .setTitle("Passwords do not match")
                    .setMessage(
                            "The two passwords do not match." +
                                    " Please enter the information again.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else if (passEditText.getText().toString().length() < 8) {
            new AlertDialog.Builder(this)
                    .setTitle("Passwords is too short")
                    .setMessage(
                            "Your password must be at least 8 characters long." +
                                    " Please reenter a longer password.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            SharedPreferences.Editor sharedPreferences =
                    getSharedPreferences(
                            getString(R.string.shared_pref_code), Context.MODE_PRIVATE).edit();
            sharedPreferences.putString(
                    getString(R.string.shared_pref_name), nameEditText.getText().toString());
            sharedPreferences.putString(
                    getString(R.string.shared_pref_email), emailEditText.getText().toString());
            sharedPreferences.putString(
                    getString(R.string.shared_pref_pass), passEditText.getText().toString());
            sharedPreferences.putString(
                    getString(R.string.shared_pref_address), addressEditText.getText().toString());
            sharedPreferences.putString(
                    getString(R.string.shared_pref_phone), phoneEditText.getText().toString());
            sharedPreferences.putString(
                    getString(R.string.shared_pref_type),
                    accountTypeSpinner.getSelectedItem().toString());

            sharedPreferences.apply();

            //add option for user to back out even after they click submit changes

            Intent goingBack = new Intent();
            goingBack.putExtra("Result", "Success");
            setResult(RESULT_OK, goingBack);
            finish();

        }
    }

     }
