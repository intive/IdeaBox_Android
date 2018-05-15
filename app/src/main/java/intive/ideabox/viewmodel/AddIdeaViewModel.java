package intive.ideabox.viewmodel;

import android.view.View;

import javax.inject.Inject;

import intive.ideabox.IdeaBoxApplication;
import intive.ideabox.model.CloudProvider;
import intive.ideabox.utility.NavigationUtils;

public class AddIdeaViewModel {

    private static final int MIN_IDEA_TEXT_LENGTH = 5;

    @Inject
    CloudProvider dataProvider;


    public AddIdeaViewModel() {
        IdeaBoxApplication.appComponent.inject(this);
    }

    public void saveIdea(View view, String userIdea) {
        if (userIdea.length() >= MIN_IDEA_TEXT_LENGTH) {
            dataProvider.saveIdea(userIdea);
            NavigationUtils.getInstance().setIdeaListState(true);
        } else
            android.widget.Toast.makeText(view.getContext(), intive.ideabox.R.string.to_short_idea,
                    android.widget.Toast.LENGTH_SHORT).show();
    }
}
