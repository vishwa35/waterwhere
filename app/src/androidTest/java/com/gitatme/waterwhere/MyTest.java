package com.gitatme.waterwhere;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.gitatme.waterwhere.view.LoginActivity;
import com.gitatme.waterwhere.view.MainActivity;
import com.gitatme.waterwhere.view.WaterReportActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.anything;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MyTest {
    String date;
    String lat;
    String lon;
    int waterType;
    int condition;
    boolean success;


    @Rule
    public final ActivityTestRule<WaterReportActivity> rule =
            new ActivityTestRule<>(WaterReportActivity.class, true, true);
    @Before
    public void init() {
        date = "123";
        lat = "24";
        lon = "r";
        waterType = 1;
        condition = 1;
        success = WaterReportActivity.getSuc();

    }
    @Test
    public void checkReportFields() throws Exception {
        assertEquals(false, success);
        onView(withId(R.id.editTextDate)).perform(typeText(date));
        onView(withId(R.id.editTextLatitude)).perform(typeText(lat));
        onView(withId(R.id.editTextLongitude)).perform(typeText(lon));
        closeSoftKeyboard();
        onView(withId(R.id.spinnerWaterType)).perform(click());
        onData(anything()).atPosition(waterType).perform(click());
        onView(withId(R.id.spinnerWaterConditions)).perform(click());
        onData(anything()).atPosition(condition).perform(click());
        onView(withId(R.id.createReportButton)).perform(click());
        assertEquals(false, success);
    }
    @Test
    public void checkFields2() throws Exception {

        date = "23";
        lat = "24";
        lon = "44";
        waterType = 2;
        condition = 2;
        success = WaterReportActivity.getSuc();

        assertEquals(false, success);
        onView(withId(R.id.editTextDate)).perform(typeText(date));
        onView(withId(R.id.editTextLatitude)).perform(typeText(lat));
        onView(withId(R.id.editTextLongitude)).perform(typeText(lon));
        closeSoftKeyboard();
        onView(withId(R.id.spinnerWaterType)).perform(click());
        onData(anything()).atPosition(waterType).perform(click());
        onView(withId(R.id.spinnerWaterConditions)).perform(click());
        onData(anything()).atPosition(condition).perform(click());
        onView(withId(R.id.createReportButton)).perform(click());
        assertEquals(true, success);
    }
    @Test
    public void checkFields3() throws Exception {

        date = "123";
        lat = "r";
        lon = "44";
        waterType = 0;
        condition = 3;
        success = WaterReportActivity.getSuc();

        assertEquals(false, success);
        onView(withId(R.id.editTextDate)).perform(typeText(date));
        onView(withId(R.id.editTextLatitude)).perform(typeText(lat));
        onView(withId(R.id.editTextLongitude)).perform(typeText(lon));
        closeSoftKeyboard();
        onView(withId(R.id.spinnerWaterType)).perform(click());
        onData(anything()).atPosition(waterType).perform(click());
        onView(withId(R.id.spinnerWaterConditions)).perform(click());
        onData(anything()).atPosition(condition).perform(click());
        onView(withId(R.id.createReportButton)).perform(click());
        assertEquals(false, success);

    }


}
