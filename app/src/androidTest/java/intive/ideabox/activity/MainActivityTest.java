package intive.ideabox.activity;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;


import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import intive.ideabox.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;


@LargeTest
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.JVM)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void basicFlow() {
//test if I can view main screen and go to add idea screen
        //asserting that idea recycler on main screen is visible
        onView(withId(R.id.idea_recycler))
        .check(matches(isDisplayed()));
        //go to add idea screen
        onView(withId(R.id.add_fab))
                .perform(click());
        //on idea screen assert that the correct screen is visible
        onView(withId(R.id.backgroundLayout))
                .check(matches(isDisplayed()));
                //.check(matches(withText("Add Your idea here:")));
        //check if editText is visible
            onView(withId(R.id.editText))
                .check(matches(isDisplayed()));
        //check if fab to add idea is visible and clickable
            onView(withId(R.id.fab))
                .check(matches(isDisplayed()))
                .check(matches((isClickable())));

        //returning to previous screen for test's sake
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.pressBack();
        onView(withId(R.id.dialog_btn_yes))
                .perform(click());

    }

    @Test
    public void addingIdeaScreen() {

        onView(withId(R.id.add_fab))
                .perform(click());

       onView(withId(R.id.editText))
                .perform(click(),typeText("My brilliant idea"),closeSoftKeyboard());
        //I have to close keyboard to find add button, scrollTo doesn't work

       onView(withId(R.id.fab))
               .perform(click());
        onView(withText(R.string.added_idea))
               .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
               .check(matches(isDisplayed()))
               .check(matches(withText("Idea has been added!")));

    }

    @Test
    public void positiveCharCountValidation_1(){
        //Boundary value test. Checking if I can add idea with minimal char count - 5
        //Steps to add idea
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("Lorem"), closeSoftKeyboard());
        onView(withId(R.id.fab))
                .perform(click());

        //Asserting that the right toast message appears and idea is added
        onView(withText(R.string.added_idea))
                .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
                //double assertion to make sure that toast message is displayed and has the right message
                .check(matches(isDisplayed()))
                .check(matches(withText("Idea has been added!")));
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void positiveCharCountValidation_2(){
        //Boundary value test. Checking if I can add idea with above minimal char count
        //Steps to add idea
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("Lorem Ipsum"), closeSoftKeyboard());
        onView(withId(R.id.fab))
                .perform(click());

        //Asserting that the right toast message appears and idea is added
        onView(withText(R.string.added_idea))
                .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
                //double assertion to make sure that toast message is displayed and has the right message
                .check(matches(isDisplayed()))
                .check(matches(withText("Idea has been added!")));
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void positiveCharCountValidation_3(){
        //Boundary value test. Checking if I can add idea with maximal char count
        //Steps to add idea
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus lobortis sollicitudin ligula, tincidunt tincidunt turpis interdum eget. Etiam magna turpis, elementum vel congue ut, dictum in velit. Integer euismod diam id rutrum sagittis. Aenean massa leo, pulvinar nec laoreet at, aliquam sit a"), closeSoftKeyboard());
        onView(withId(R.id.fab))
                .perform(click());

        //Asserting that the right toast message appears and idea is added
        onView(withText(R.string.added_idea))
                .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
                //double assertion to make sure that toast message is displayed and has the right message
                .check(matches(isDisplayed()))
                .check(matches(withText("Idea has been added!")));
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void negativeCharCountValidation_1(){
        //Boundary value test. Asserts that user can't add idea which does not meet minimal requirements.
        //Steps to add idea
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("L"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.fab))
                .perform(click());

        //Asserting that the right toast message appears and idea is added
        onView(withText(R.string.to_short_idea))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                //double assertion to make sure that toast message is displayed and has the right message
                .check(matches(isDisplayed()))
                .check(matches(withText("The text is too short, please provide a longer idea.")));

        //returning to previous screen for test's sake
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.pressBack();
        onView(withId(R.id.dialog_btn_yes))
                .perform(click());
    }

    @Test
    public void negativeCharCountValidation_2(){
        //Boundary value test. Asserts that user can't add idea which does not meet minimal requirements.
        //Steps to add idea
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("Lo"), closeSoftKeyboard());
        onView(withId(R.id.fab))
                .perform(click());

        //Asserting that the right toast message appears and idea is added
        onView(withText(R.string.to_short_idea))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                //double assertion to make sure that toast message is displayed and has the right message
                .check(matches(isDisplayed()))
                .check(matches(withText("The text is too short, please provide a longer idea.")));

        //returning to previous screen for test's sake
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.pressBack();
        onView(withId(R.id.dialog_btn_yes))
                .perform(click());}
    @Test
    public void counterShowsCharactersLeft(){
        //Steps to add idea
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus lobortis sollicitudin ligula, tincidunt tincidunt turpis interdum eget. Etiam magna turpis, elementum vel congue ut, dictum in velit. Integer euismod diam id rutrum sagittis. Aenean massa !"),
                        closeSoftKeyboard());
        onView(withId(R.id.counterTextCharacters))
                .check(matches(withText("0/256")));

        //returning to previous screen for test's sake
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.pressBack();
        onView(withId(R.id.dialog_btn_yes))
                .perform(click());}


    @Test
    public void exitWithoutSaving(){

        // steps to prepare app to test
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus lobortis sollicitudin ligula, tincidunt tincidunt turpis interdum eget. Etiam magna turpis, elementum vel congue ut, dictum in velit."), closeSoftKeyboard());

        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.pressBack();

        onView(withId(R.id.dialog_text))
        .check(matches(isDisplayed()));
        //buttons are showing
        onView(withId(R.id.dialog_buttons_background))
                .check(matches(isDisplayed()));
        //are two buttons
        onView(withId(R.id.dialog_btn_no))
                .check(matches(isClickable()));
        onView(withId(R.id.dialog_btn_yes))
                .check(matches(isClickable()));

        //returning to previous screen for test's sake
        onView(withId(R.id.dialog_btn_yes))
                .perform(click());
    }

    @Test
    public void quitWithoutSavingPositiveHandling(){
        //what will happen when I click "yes" button

        // steps to prepare app to test
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus lobortis sollicitudin ligula, tincidunt tincidunt turpis interdum eget. Etiam magna turpis, elementum vel congue ut, dictum in velit."), closeSoftKeyboard());

//pressing back button
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.pressBack();

        onView(withId(R.id.dialog_btn_yes))
                .perform(click());

        //asserting that user lands on main screen
        onView(withId(R.id.idea_recycler))
                .check(matches(isDisplayed()));
    }

    @Test
    public void quitWithoutSavingNegativeHandling(){
        //what will happen when I click "no" button

        // steps to prepare app to test
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus lobortis sollicitudin ligula, tincidunt tincidunt turpis interdum eget. Etiam magna turpis, elementum vel congue ut, dictum in velit."), closeSoftKeyboard());

    //pressing back button
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.pressBack();

        onView(withId(R.id.dialog_btn_no))
                .perform(click());

        //asserting that user stays on current page
        onView(withId(R.id.editText))
                .check(matches(isDisplayed()));

        //returning to previous screen for test's sake
        UiDevice phone = UiDevice.getInstance(getInstrumentation());
        phone.pressBack();
        onView(withId(R.id.dialog_btn_yes))
                .perform(click());
    }
}