package intive.ideabox.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import intive.ideabox.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.JVM)

public class ScreenAdmin {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void loginScreen(){
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        // Click the item
        onView(withText("Login"))
                .perform(click());

        onView(withId(R.id.text_input_email))
                .perform(click() , typeText("joanna.roslaniec@gmail.com"));
        onView(withId(R.id.text_input_password))
                .perform(click(), typeText("test123"));
        onView(withId(R.id.login_button))
                .perform(click());

        onView(withId(R.id.idea_recycler))
            .check(matches(isDisplayed()));

    }
}
