package com.hoboss.phonenumberspinnerespresso;

import android.content.Context;
import android.widget.AdapterView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SpinnerSelectionTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.hoboss.phonenumberspinnerespresso", appContext.getPackageName());
    }

    @Test
    public void iterateSpinnerItems() {
        // Be careful in an AdapterView such as a Spinner. This means there is a possibility
        // that the View you want to test may not be in the View hierarchy at that time.
        // The Espresso API handles this problem by providing a separate onData() entry point,
        // which is able to first load the adapter item and bring it into focus prior to locating
        // and performing actions on any of its children.
        String[] myArray = mActivityRule.getActivity().getResources()
                .getStringArray(R.array.labels_array);

        int size = myArray.length;
        for (int i = 0; i < size; i++) {
            onView(withId(R.id.label_spinner)).perform(click());
            // Find the Spinner item and click on it.
            onData(is(myArray[i])).perform(click());
            // Find the text view and check that the spinner item is part of the string.
            onView(withId(R.id.text_phonelabel)).check(matches(withText(containsString(myArray[i]))));
        }
    }
}
