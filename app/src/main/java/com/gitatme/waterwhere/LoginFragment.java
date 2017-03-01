package com.gitatme.waterwhere;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    EditText email;
    EditText pass;
    TextInputLayout emailLayout;
    TextInputLayout passLayout;
    Button login;
    Button cancel;

    public LoginFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_pref_code), Context.MODE_PRIVATE);
        String idealEmail = sharedPreferences.getString(getString(R.string.shared_pref_email), "email");
        String idealPass = sharedPreferences.getString(getString(R.string.shared_pref_pass), "pass");
        if (email.getText().toString().trim().equals(idealEmail) && pass.getText().toString().trim().equals(idealPass)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(getString(R.string.shared_pref_loggedin), true);
            editor.commit();
            Intent mainactivity = new Intent(getActivity(), MainActivity.class);
            startActivity(mainactivity);
            getActivity().finish();
        } else {
            Toast.makeText(getActivity(), "Could not find account associated with these credentials", Toast.LENGTH_SHORT).show();
        }
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

}
