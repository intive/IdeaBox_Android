package intive.ideabox.viewmodel;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import intive.ideabox.R;
import intive.ideabox.fragment.IdeaListFragment;
import intive.ideabox.model.FirebaseProvider;

public class AddIdeaViewModel {

    private static final int MIN_IDEA_TEXT_LENGTH = 3;

    private FragmentActivity fragmentActivity;

    public AddIdeaViewModel(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    public void saveIdea(String userIdea) {
        if (userIdea.length() < MIN_IDEA_TEXT_LENGTH) {
            Toast.makeText(fragmentActivity.getApplicationContext(), R.string.to_short_idea, Toast.LENGTH_SHORT).show();
         } else {
            FirebaseProvider dataProvider = FirebaseProvider.getInstance();
            dataProvider.saveIdea(userIdea);
            fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, IdeaListFragment.newInstance(true)).commit();
        }
    }
}