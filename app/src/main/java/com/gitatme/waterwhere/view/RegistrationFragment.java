package com.gitatme.waterwhere.view;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gitatme.waterwhere.R;
import com.gitatme.waterwhere.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    EditText email;
    TextInputLayout emailLayout;
    EditText pass;
    TextInputLayout passLayout;
    EditText passConfirm;
    TextInputLayout passConfirmLayout;
    EditText name;
    TextInputLayout nameLayout;
    EditText address;
    TextInputLayout addressLayout;
    EditText phone;
    TextInputLayout phoneLayout;
    Spinner type;
    Button register;
    Button cancel;
    private static ArrayList<User> registeredUsers = new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Firebase
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    //not logged in
                } else {
                    //logged in
                }
            }
        };
        mDatabase = FirebaseDatabase.getInstance().getReference();

        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        email = (EditText) view.findViewById(R.id.register_edittext_email);
        emailLayout = (TextInputLayout) view.findViewById(R.id.register_textinputlayout_email);
        pass = (EditText) view.findViewById(R.id.register_edittext_pass);
        passLayout = (TextInputLayout) view.findViewById(R.id.register_textinputlayout_pass);
        passConfirm = (EditText) view.findViewById(R.id.register_edittext_confirmpass);
        passConfirmLayout = (TextInputLayout) view.findViewById(R.id.register_textinputlayout_confirmpass);
        name = (EditText) view.findViewById(R.id.register_edittext_name);
        nameLayout = (TextInputLayout) view.findViewById(R.id.register_textinputlayout_name);
        address = (EditText) view.findViewById(R.id.register_edittext_address);
        addressLayout = (TextInputLayout) view.findViewById(R.id.register_textinputlayout_address);
        phone = (EditText) view.findViewById(R.id.register_edittext_phone);
        phoneLayout = (TextInputLayout) view.findViewById(R.id.register_textinputlayout_phone);
        type = (Spinner) view.findViewById(R.id.register_spinner_type);
        register = (Button) view.findViewById(R.id.register_button_signup);
        cancel = (Button) view.findViewById(R.id.register_button_cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent onboarding = new Intent(getActivity(), OnboardingActivity.class);
                startActivity(onboarding);
                getActivity().finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()) {
                    registerUser();
                }
            }
        });
        return view;
    }

    /**
     * Registers the user with firebase authentication.
     */
    public void registerUser() {
//        editor.putString(getString(R.string.shared_pref_email), email.getText().toString().trim());
//        editor.putString(getString(R.string.shared_pref_pass), pass.getText().toString().trim());
//        editor.putString(getString(R.string.shared_pref_name), name.getText().toString().trim());
//        editor.putString(getString(R.string.shared_pref_address), address.getText().toString().trim());
//        editor.putString(getString(R.string.shared_pref_phone), phone.getText().toString().trim());
//        editor.putString(getString(R.string.shared_pref_type), type.getSelectedItem().toString());

        mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), pass.getText().toString().trim())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            try {
                                Snackbar.make(getView(),"Sorry! We weren't able to register your account.", Snackbar.LENGTH_LONG).show();
                            } catch (NullPointerException e) {
                                Toast.makeText(getContext(), "Sorry! We weren't able to register your account.", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            FirebaseUser user = mAuth.getCurrentUser();

                            //set user metadata
                            DatabaseReference thisUser = mDatabase.child("users").child(user.getUid());

                            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name.getText().toString().trim()).build();
                            user.updateProfile(profileChangeRequest);

                            thisUser.child("name").setValue(name.getText().toString().trim());
                            thisUser.child("address").setValue(address.getText().toString().trim());
                            thisUser.child("phone").setValue(phone.getText().toString().trim());
                            thisUser.child("type").setValue(type.getSelectedItem().toString());
//                            Long tsLong = System.currentTimeMillis()/1000;
//                            thisUser.child("timestamp").setValue(tsLong.toString());

                            //send confirmation email to the user
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (getContext() != null) {
//                                        Toast.makeText(getContext(), "Success! A verification email has been sent.", Toast.LENGTH_SHORT).show();
//                                    }
                                    Intent dashboard = new Intent(getActivity(), MainActivity.class);
                                    startActivity(dashboard);
                                }
                            });
                        }
                    }
                });
    }

    /*
     * Validates the fields for registration.
     *
     * @return true if all validate, false otherwise
     */
    public boolean validateFields() {
        //email
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email.getText().toString().trim());
        if (email.getText().toString().trim().isEmpty()) {
            emailLayout.setError(getString(R.string.register_textinputlayout_email_empty));
            email.clearFocus();
            email.requestFocus();
            return false;
        } else if (!matcher.find()) {
            emailLayout.setError(getString(R.string.register_textinputlayout_email_invalid));
            email.clearFocus();
            email.requestFocus();
            return false;
        }
        else {
            emailLayout.setErrorEnabled(false);
        }

        //pass
        if (pass.getText().toString().trim().isEmpty()) {
            passLayout.setError(getString(R.string.register_textinputlayout_pass_empty));
            pass.clearFocus();
            pass.requestFocus();
            return false;
        } else if (pass.getText().toString().trim().length() < 8) {
            passLayout.setError(getString(R.string.register_textinputlayout_pass_length));
            pass.clearFocus();
            pass.requestFocus();
            return false;
        } else {
            passLayout.setErrorEnabled(false);
        }

        //confirm pass
        if (passConfirm.getText().toString().trim().isEmpty()) {
            passConfirmLayout.setError(getString(R.string.register_textinputlayout_confirmpass_empty));
            passConfirm.clearFocus();
            passConfirm.requestFocus();
            return false;
        } else if (!passConfirm.getText().toString().trim().equals(pass.getText().toString().trim())) {
            passConfirmLayout.setError(getString(R.string.register_textinputlayout_confirmpass_match));
            passConfirm.clearFocus();
            passConfirm.requestFocus();
            return false;
        } else {
            passConfirmLayout.setErrorEnabled(false);
        }

        //name
        if (name.getText().toString().trim().isEmpty()) {
            nameLayout.setError(getString(R.string.register_textinputlayout_name_empty));
            name.clearFocus();
            name.requestFocus();
            return false;
        } else {
            nameLayout.setErrorEnabled(false);
        }

        //address
        if (address.getText().toString().trim().isEmpty()) {
            addressLayout.setError(getString(R.string.register_textinputlayout_address_empty));
            address.clearFocus();
            address.requestFocus();
            return false;
        } else {
            addressLayout.setErrorEnabled(false);
        }

        //phone
        if (phone.getText().toString().trim().isEmpty()) {
            phoneLayout.setError(getString(R.string.register_textinputlayout_phone_empty));
            phone.clearFocus();
            phone.requestFocus();
            return false;
        } else if (!validatePhoneNumber(phone.getText().toString().trim())) {
            phoneLayout.setError(getString(R.string.register_textinputlayout_phone_invalid));
            phone.clearFocus();
            phone.requestFocus();
            return false;
        } else {
            phoneLayout.setErrorEnabled(false);
        }

        return true;
    }

    /*
     * Validates phone numbers of various types
     *
     * @return true if the phone num is valid, false if not
     */
    private boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else return false;

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}