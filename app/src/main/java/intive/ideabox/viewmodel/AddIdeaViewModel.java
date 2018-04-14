package intive.ideabox.viewmodel;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import intive.ideabox.R;
import intive.ideabox.fragment.IdeaListFragment;
import intive.ideabox.model.FirebaseProvider;

public class AddIdeaViewModel {

    private static final int MIN_IDEA_TEXT_LENGTH = 3;

    private FragmentActivity mFragmentActivity;

    public AddIdeaViewModel(FragmentActivity fragmentActivity) {
        mFragmentActivity = fragmentActivity;
    }

    public void saveIdea(String userIdea) {
        if (userIdea.length() < MIN_IDEA_TEXT_LENGTH) {
            Toast.makeText(mFragmentActivity.getApplicationContext(), R.string.to_short_idea, Toast.LENGTH_SHORT).show();
         } else {
            FirebaseProvider dataProvider = FirebaseProvider.getInstance();
            dataProvider.saveIdea(userIdea);
            mFragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, IdeaListFragment.newInstance(true)).commit();
        }
    }
    public void setupParent(View view) {
        if(!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard();
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupParent(innerView);
            }
        }
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) mFragmentActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View focusedView = mFragmentActivity.getCurrentFocus();

        if (focusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(mFragmentActivity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}