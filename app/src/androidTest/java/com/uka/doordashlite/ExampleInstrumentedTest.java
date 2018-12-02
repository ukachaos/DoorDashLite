package com.uka.doordashlite;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    MainActivity mainActivity;

    @Before
    public void init() {
        mainActivity = activityActivityTestRule.getActivity();
    }

    @Test
    public void testRecyclerView() {
        onView(withId(R.id.mMainList)).check(new RecyclerViewItemCountAssertion(1));
    }
}
