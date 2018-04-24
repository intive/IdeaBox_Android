package intive.ideabox.viewmodel;

import intive.ideabox.model.FirebaseProvider;
import intive.ideabox.utility.NavigationUtils;

public class AddIdeaViewModel {

    private static final int MIN_IDEA_TEXT_LENGTH = 3;

    public AddIdeaViewModel() {
    }

    public void saveIdea(String userIdea) {
        if (userIdea.length() >= MIN_IDEA_TEXT_LENGTH) {
            FirebaseProvider dataProvider = FirebaseProvider.getInstance();
            dataProvider.saveIdea(userIdea);
            NavigationUtils.getInstance().setIdeaListState(true);
        }
    }
}
