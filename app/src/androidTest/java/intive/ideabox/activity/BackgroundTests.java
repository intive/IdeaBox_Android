package intive.ideabox.activity;

import android.os.RemoteException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import intive.ideabox.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.JVM)
public class BackgroundTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void goToBackgroundAndBack_a() throws UiObjectNotFoundException, RemoteException {
        //Testing going back from background
        //asserting that main screen is loaded
        onView(withId(R.id.idea_recycler))
                .check(matches(isDisplayed()));

        //reach device's controller go to background and back
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.pressHome();
        device.pressRecentApps();
        device.findObject(new UiSelector().text(getTargetContext().getString(getTargetContext().getApplicationInfo().labelRes)))
                .click();

        //asserting that app got back on right screen
        onView(withId(R.id.idea_recycler))
                .check(matches(isDisplayed()));

    }

    @Test
    public void goToBackgroundAndBack_b() throws RemoteException, UiObjectNotFoundException {
        //Testing going back from background
        //steps to go to the idea screen
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .check(matches(isDisplayed()));

        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.pressHome();
        device.pressRecentApps();
        device.findObject(new UiSelector().text(getTargetContext().getString(getTargetContext().getApplicationInfo().labelRes)))
                .click();

        //asserting that app got back on right screen
        onView(withId(R.id.editText))
                .check(matches(isDisplayed()));

    }


}
