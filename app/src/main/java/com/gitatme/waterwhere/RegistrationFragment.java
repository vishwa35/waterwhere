package com.gitatme.waterwhere;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
     * If inputs are valid, the user is registered and his or her credentials and information are saved.
     * Launches MainActivity.
     */
    public void registerUser() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_pref_code), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.shared_pref_email), email.getText().toString().trim());
        editor.putString(getString(R.string.shared_pref_pass), pass.getText().toString().trim());
        editor.putString(getString(R.string.shared_pref_name), name.getText().toString().trim());
        editor.putString(getString(R.string.shared_pref_address), address.getText().toString().trim());
        editor.putString(getString(R.string.shared_pref_phone), phone.getText().toString().trim());
        editor.putString(getString(R.string.shared_pref_type), type.getSelectedItem().toString());
        editor.putBoolean(getString(R.string.shared_pref_loggedin), true);

        editor.commit();

        User newUser = new User(name.getText().toString().trim(), email.getText().toString().trim(),
                phone.getText().toString().trim(), pass.getText().toString().trim(), address.getText().toString().trim(),
                type.getSelectedItem().toString());
        registeredUsers.add(newUser);
        for (User user: registeredUsers) {
            Log.e("User: ", user.toString());
        }

        Intent mainactivity = new Intent(getActivity(), MainActivity.class);
        startActivity(mainactivity);
        getActivity().finish();
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

}
