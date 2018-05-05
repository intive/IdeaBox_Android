package intive.ideabox.activity;

import android.os.RemoteException;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

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
import static com.facebook.stetho.inspector.network.ResponseHandlingInputStream.TAG;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BackgroundTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void goToBackgroundAndBack_a() throws RemoteException, UiObjectNotFoundException {

        onView(withId(R.id.idea_recycler))
                .check(matches(isDisplayed()));

         UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.pressHome();
        device.pressRecentApps();
        device.findObject(new UiSelector().text(getTargetContext().getString(getTargetContext().getApplicationInfo().labelRes)))
                .click();

        try { onView(withId(R.id.idea_recycler))
                .check(matches(isDisplayed()));
            Log.d(TAG, "APPLICATION DID NOT CRUSHED");
    } catch (NoMatchingViewException e){
        }

    }

    @Test
    public void goToBackgroundAndBack_b() throws RemoteException, UiObjectNotFoundException {

        onView(withId(R.id.add_fab))
                .perform(click());

        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.pressHome();
        device.pressRecentApps();
        device.findObject(new UiSelector().text(getTargetContext().getString(getTargetContext().getApplicationInfo().labelRes)))
                .click();

        try { onView(withId(R.id.editText))
                .check(matches(isDisplayed()));
            Log.d(TAG, "APPLICATION DID NOT CRUSHED");
        } catch (NoMatchingViewException e){
        }

    }


}
