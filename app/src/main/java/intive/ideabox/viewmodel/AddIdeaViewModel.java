package intive.ideabox.viewmodel;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import intive.ideabox.R;
import intive.ideabox.model.FirebaseProvider;
import intive.ideabox.utility.FragmentState;
import intive.ideabox.utility.NavigationUtils;

public class AddIdeaViewModel {

    private static final int MIN_IDEA_TEXT_LENGTH = 5;
    private FragmentActivity fragmentActivity;
    public AddIdeaViewModel(FragmentActivity appFragment) {
        this.fragmentActivity = appFragment;
    }

    public void saveIdea(View view, String userIdea) {
        if (userIdea.length() >= MIN_IDEA_TEXT_LENGTH) {
            FirebaseProvider dataProvider = FirebaseProvider.getInstance();
            dataProvider.saveIdea(userIdea,fragmentActivity );
            NavigationUtils.getInstance().setSnackBar(R.string.added_idea);
            NavigationUtils.getInstance().setState(FragmentState.IdeaList);
        } else
            android.widget.Toast.makeText(view.getContext(), intive.ideabox.R.string.to_short_idea,
                    android.widget.Toast.LENGTH_SHORT).show();
    }
}
