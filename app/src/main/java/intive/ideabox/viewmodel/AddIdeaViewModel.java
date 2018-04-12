package intive.ideabox.viewmodel;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import intive.ideabox.R;
import intive.ideabox.fragment.IdeaListFragment;
import intive.ideabox.model.FirebaseProvider;

public class AddIdeaViewModel {

    private static final int MIN_IDEA_TEXT_LENGTH = 3;
    private static final int MAX_IDEA_TEXT_LENGTH = 256;

    private FragmentActivity mFragmentActivity;

    public AddIdeaViewModel(FragmentActivity fragmentActivity) {
        mFragmentActivity = fragmentActivity;
    }

    public void saveIdea(String userIdea) {
        if (userIdea.length() < MIN_IDEA_TEXT_LENGTH) {
            Toast.makeText(mFragmentActivity.getApplicationContext(), R.string.to_short_idea, Toast.LENGTH_SHORT).show();
        } else if (userIdea.length() > MAX_IDEA_TEXT_LENGTH) {
            Toast.makeText(mFragmentActivity.getApplicationContext(), R.string.to_long_idea, Toast.LENGTH_SHORT).show();
        } else {
            FirebaseProvider dataProvider = FirebaseProvider.getInstance();
            dataProvider.saveIdea(userIdea);
            mFragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, IdeaListFragment.newInstance(true)).commit();
        }
    }
}