package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.androiduitesting.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    private static final String TEST_CITY = "Edmonton";

    // 1) Check whether the activity correctly switched
    @Test
    public void testActivitySwitch() {
        // Click first item in the ListView
        onData(anything()).atPosition(0).perform(click());

        // If switched to ShowActivity, its TextView should be visible
        onView(withId(R.id.textView2)).check(matches(isDisplayed()));
    }

    // 2) Test whether the city name is consistent
    @Test
    public void testCityNameConsistency() {
        Context appContext = ApplicationProvider.getApplicationContext();

        Intent intent = new Intent(appContext, ShowActivity.class);
        intent.putExtra(ShowActivity.EXTRA_CITY_NAME, TEST_CITY);

        ActivityScenario.launch(intent);

        onView(withId(R.id.textView2)).check(matches(withText(TEST_CITY)));
    }

    @Test
    public void testBackButton() {
        // 1) From MainActivity, open ShowActivity by clicking a city
        onData(anything()).atPosition(0).perform(click());

        onView(withId(R.id.textView2)).check(matches(isDisplayed()));

        // 2) Click the back button in ShowActivity
        onView(withId(R.id.button)).perform(click());

        // 3) Verify we are back on MainActivity (city list displayed)
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}