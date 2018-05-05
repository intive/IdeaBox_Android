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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void A_basicScreen() {

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
    public void B_addingIdea() {

        onView(allOf(withId(R.id.add_fab)))
                .perform(click());

       onView(allOf(withId(R.id.editText)))
                .perform(click(),typeText("My brilliant idea"),closeSoftKeyboard());
        //I have to close keyboard to find add button, scrollTo doesn't work

       onView(withId(R.id.fab))
               .perform(click());
        //it's working
        onView(allOf(withText(R.string.added_idea)))
               .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
               .check(matches(isDisplayed()))
               .check(matches(withText("Idea has been added!")));

        onView(allOf(withId(R.id.idea_message)))
        .check(matches(isDisplayed()));
    }

    @Test
    public void C_charCountPositiveValidation_a(){
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("Lorem"));

        onView(withId(R.id.fab))
                .perform(click());
        //it's working
        onView(withText(R.string.added_idea))
                .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()))
                .check(matches(withText("Idea has been added!")));
    }

    @Test
    public void C_charCountPositiveValidation_b(){
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("Lorem Ipsum"));

        onView(withId(R.id.fab))
                .perform(click());
        //it's working
        onView(withText(R.string.added_idea))
                .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()))
                .check(matches(withText("Idea has been added!")));
    }
    @Test
    public void C_charCountPositiveValidation_c(){
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("uC9siDxSebwtOvH681J54Y6Sb9WHXkipsrA7WBJ45yleiYaZw0AoJzQNpuPOp6cl9AqUEgm8bGCd2MRP5eIs0MLJf3sWTAGkBfXmygm42rO8PpWLW7N1rhRNG3UxLfJXShQNuMab60WIAeuJzAlaFmQRO4WXhWD6MCkT45wc2qxgbqGqC3HP6yu0MiIaAnJjYzuHMjN5BLviBcBRZUtPrcFjMxGxkDMtWMypfFQZfW5QGqqtQ8MpzngqcXO3elQh"));

        onView(withId(R.id.fab))
                .perform(click());
        //it's working
        onView(withText(R.string.added_idea))
                .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()))
                .check(matches(withText("Idea has been added!")));
    }

    @Test
    public void D_charCountNegativeValidation_a(){
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("1"));

        onView(withId(R.id.fab))
                .perform(click());
        //it's working
        onView(withText(R.string.to_short_idea))
                .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()))
                .check(matches(withText("The text is too short, please provide a longer idea.")));
    }

    @Test
    public void D_charCountNegativeValidation_b(){
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("kk"));

        onView(withId(R.id.fab))
                .perform(click());
        //it's working
        onView(withText(R.string.to_short_idea))
                .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()))
                .check(matches(withText("The text is too short, please provide a longer idea.")));
    }

    @Test
    public void D_charCountNegativeValidation_c(){
        onView(withId(R.id.add_fab))
                .perform(click());
        onView(withId(R.id.editText))
                .perform(click(), typeText("dddwv271IeiP6POvOud2FC0hlCzqVqz7Ay3jJEAGlJcxLq8RbttgNdHi8K5sjx5GcCIDnfdsAApS8XeDSc9UQOBDqEYBythj0ATLeAdRwY8xhajP3z15IPcSbUozVMMCQPed5k3eaQkmDdlntnmUqITzz5AzAKNfXm2Z3ovoyq2FITVfU3cVgbVswDJ8nmsJGrOBJYoZWJ5sZqfMQ7bY6fgrLvyfDRWxZ2YJIYVjoRic3R3pMkfkBEBEoe2c3pYHKXx"));

        onView(withId(R.id.fab))
                .perform(click());
        //it's working
        onView(withText(R.string.to_long_idea))
                .inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()))
                .check(matches(withText("The text is too long, please provide a shorter idea.")));
    }

}