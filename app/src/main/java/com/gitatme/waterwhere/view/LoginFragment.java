package com.gitatme.waterwhere.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    //Android
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    EditText email;
    EditText pass;
    TextInputLayout emailLayout;
    TextInputLayout passLayout;
    Button login;
    Button cancel;

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public LoginFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Firebase
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //logged in - send to main activity
                    Intent mainactivity = new Intent(getActivity(), MainActivity.class);
                    startActivity(mainactivity);
                    getActivity().finish();
                }
            }
        };

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        email = (EditText) view.findViewById(R.id.login_edittext_email);
        pass = (EditText) view.findViewById(R.id.login_edittext_pass);
        emailLayout = (TextInputLayout) view.findViewById(R.id.login_textinputlayout_email);
        passLayout = (TextInputLayout) view.findViewById(R.id.login_textinputlayout_pass);
        login = (Button) view.findViewById(R.id.login_button_login);
        cancel = (Button) view.findViewById(R.id.login_button_cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent onboarding = new Intent(getActivity(), OnboardingActivity.class);
                startActivity(onboarding);
                getActivity().finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    loginUser();
                }
            }
        });
        return view;
    }

    public void loginUser() {

        mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), pass.getText().toString().trim())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user.
                        if (!task.isSuccessful()) {
                            try {
                                Snackbar.make(getView(),"Sorry! We couldn't find an account associated with these credentials", Snackbar.LENGTH_LONG).show();
                            } catch (NullPointerException e) {
                                Toast.makeText(getContext(), "Sorry! We couldn't find an account associated with these credentials", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            //login successful
                            Intent mainactivity = new Intent(getActivity(), MainActivity.class);
                            startActivity(mainactivity);
                            getActivity().finish();
                        }


                    }
                });

//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_pref_code), Context.MODE_PRIVATE);
//        String idealEmail = sharedPreferences.getString(getString(R.string.shared_pref_email), "email");
//        String idealPass = sharedPreferences.getString(getString(R.string.shared_pref_pass), "pass");
//        if (email.getText().toString().trim().equals(idealEmail) && pass.getText().toString().trim().equals(idealPass)) {
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean(getString(R.string.shared_pref_loggedin), true);
//            editor.commit();
//            Intent mainactivity = new Intent(getActivity(), MainActivity.class);
//            startActivity(mainactivity);
//            getActivity().finish();
//        } else {
//            Toast.makeText(getActivity(), "Could not find account associated with these credentials", Toast.LENGTH_SHORT).show();
//        }
    }

    /*
     * Validates the fields as a precursor for login.
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
            Toast.makeText(getActivity(), "Could not find account associated with these credentials", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getActivity(), "Could not find account associated with these credentials", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            passLayout.setErrorEnabled(false);
        }

        return true;
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
