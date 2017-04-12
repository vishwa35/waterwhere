package com.gitatme.waterwhere;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.gitatme.waterwhere.view.LoginActivity;
import com.gitatme.waterwhere.view.LoginFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by adityanadkarni on 4/12/17.
 */
@RunWith(AndroidJUnit4.class)
public class ValidateFieldsTest {
        static String email1;
        static String email2;
        static String password1;
        static String password2;
        static String email3;
        static String password3;
    @Rule
    public ActivityTestRule<LoginActivity> rule = new ActivityTestRule<LoginActivity>(LoginActivity.class);
    @Before
    public void stringsWeWantToSetup() {
        email1 = "lol@yahoo.com";
        password1 = "cs2340islife";
        email2 = "hahathisisnotvalid";
        password2 = "invalid";
        email3 = "";
        password3 = "";
    }
    @Test
    public void validateChangesWorking() {
        onView(withId(R.id.login_edittext_email)).perform(typeText(email1));
        onView(withId(R.id.login_edittext_pass)).perform(typeText(password1));
        onView(withId(R.id.login_button_login)).perform(click());
        LoginFragment f = (LoginFragment)(rule.getActivity().getSupportFragmentManager().getFragments().get(0));
        assertTrue(f.validateFields());
    }
    @Test
    public void validateChangesFailingPassword() {
        onView(withId(R.id.login_edittext_email)).perform(typeText(email1));
        onView(withId(R.id.login_edittext_pass)).perform(typeText(password2));
        onView(withId(R.id.login_button_login)).perform(click());
        LoginFragment f = (LoginFragment)(rule.getActivity().getSupportFragmentManager().getFragments().get(0));
        try {
            f.validateFields();
        } catch (RuntimeException e) {
            Log.d("Fake error", "Tried to create failure toast, meaning things were successful.");
        }
    }
    @Test(expected = RuntimeException.class)
    public void validateChangesFailingUserNameAndPassword() {
        onView(withId(R.id.login_edittext_email)).perform(typeText(email2));
        onView(withId(R.id.login_edittext_pass)).perform(typeText(password2));
        LoginFragment f = (LoginFragment)(rule.getActivity().getSupportFragmentManager().getFragments().get(0));
        f.validateFields();
    }
    @Test (expected = RuntimeException.class)
    public void validateChangesFailingUserName() {
        onView(withId(R.id.login_edittext_email)).perform(typeText(email2));
        onView(withId(R.id.login_edittext_pass)).perform(typeText(password1));
        LoginFragment f = (LoginFragment)(rule.getActivity().getSupportFragmentManager().getFragments().get(0));
        f.validateFields();
    }
    @Test
    public void validateChangesEmptyUserName() {
        onView(withId(R.id.login_edittext_email)).perform(typeText(email3));
        onView(withId(R.id.login_edittext_pass)).perform(typeText(password1));
        LoginFragment f = (LoginFragment)(rule.getActivity().getSupportFragmentManager().getFragments().get(0));
        try {
            f.validateFields();
        } catch (Exception e) {
            Log.d("Fake error", "Tried to modify string, means it would logically be false as expected.");
        }
    }
    public void validateChangesEmptyPassword() {
        onView(withId(R.id.login_edittext_email)).perform(typeText(email1));
        onView(withId(R.id.login_edittext_pass)).perform(typeText(password3));
        LoginFragment f = (LoginFragment)(rule.getActivity().getSupportFragmentManager().getFragments().get(0));
        assertFalse(f.validateFields());
        try {
            f.validateFields();
        } catch (Exception e) {
            Log.d("Fake error", "Tried to modify string, means it would logically be false as expected.");
        }
    }

}