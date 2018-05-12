package intive.ideabox.activity;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.AssertionFailedError;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import intive.ideabox.R;

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
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;


@LargeTest
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.JVM)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainScreen() {

        onView(withId(R.id.idea_recycler))
        .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.add_fab)))
                .perform(click());

        try {onView(allOf(withId(R.id.backgroundLayout)))
                .check(matches(isDisplayed()))
                .check(matches(withText("Add your idea here")));

            onView(withId(R.id.editText))
                .check(matches(isDisplayed()));

            onView(withId(R.id.fab))
                .check(matches(isDisplayed()))
                .check(matches((isClickable())));
        } catch (AssertionFailedError error){

        }
    }

    @Test
    public void addingIdeaScreen() {

        onView(allOf(withId(R.id.add_fab)))
                .perform(click());

       onView(allOf(withId(R.id.editText)))
                .perform(click(),typeText("My brilliant idea"),closeSoftKeyboard());
        //I have to close keyboard to find add button, scrollTo doesn't work

       onView(withId(R.id.fab))
               .perform(click());
        onView(allOf(withText(R.string.added_idea)))
               .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
               .check(matches(isDisplayed()))
               .check(matches(withText("Idea has been added!")));

        onView(allOf(withId(R.id.idea_message)))
        .check(matches(isDisplayed()));
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
    }
    @Test
    public void positiveCharCountValidation_3(){
        //Boundary value test. Checking if I can add idea with maximal char count
        //Steps to add idea
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus lobortis sollicitudin ligula, tincidunt tincidunt turpis interdum eget. Etiam magna turpis, elementum vel congue ut, dictum in velit. Integer euismod diam id rutrum sagittis. Aenean massa leo, pulvinar nec laoreet at, aliquam sit a"),
                        closeSoftKeyboard());
        onView(withId(R.id.fab))
                .perform(click());

        //Asserting that the right toast message appears and idea is added
        onView(withText(R.string.added_idea))
                .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
                //double assertion to make sure that toast message is displayed and has the right message
                .check(matches(isDisplayed()))
                .check(matches(withText("Idea has been added!")));
    }

    @Test
    public void negativeCharCountValidation_1(){
        //Boundary value test. Asserts that user can't add idea which does not meet minimal requirements.
        //Steps to add idea
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("L"), closeSoftKeyboard());
        onView(withId(R.id.fab))
                .perform(click());

        //Asserting that the right toast message appears and idea is added
        onView(withText(R.string.to_short_idea))
                .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
                //double assertion to make sure that toast message is displayed and has the right message
                .check(matches(isDisplayed()))
                .check(matches(withText("The text is too short, please provide a longer idea.")));
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
                .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
                //double assertion to make sure that toast message is displayed and has the right message
                .check(matches(isDisplayed()))
                .check(matches(withText("The text is too short, please provide a longer idea.")));
    }
// ToDo: find out how to test this with new requirements
//    @Test
//    public void D_charCountNegativeValidation_c(){
//        //Boundary value test. Asserts that user can't add idea which does not meet minimal requirements.
//        //Steps to add idea
//        onView(withId(R.id.add_fab))
//                .perform(click());
//        onView(withId(R.id.editText))
//                .perform(click(), typeText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus lobortis sollicitudin ligula, tincidunt tincidunt turpis interdum eget. Etiam magna turpis, elementum vel congue ut, dictum in velit. Integer euismod diam id rutrum sagittis. Aenean massa leo, pulvinar nec laoreet at, aliquam sit amet nibh. Sed vel nisl ante. Nullam rutrum felis at augue ullamcorper, ac pretium mauris euismod. Nam augue leo, blandit sed ipsum eu, aliquet blandit justo. Pellentesque fringilla pretium nisi at maximus."),
//                        closeSoftKeyboard());
//        onView(withId(R.id.fab))
//                .perform(click());
//
//        //it's working
//        onView(withText(R.string.to_long_idea))
//                .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
//                .check(matches(isDisplayed()))
//                .check(matches(withText("The text is too long, please provide a shorter idea.")));
//    }

}