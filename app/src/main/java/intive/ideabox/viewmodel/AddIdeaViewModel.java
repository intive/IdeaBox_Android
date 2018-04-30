package intive.ideabox.viewmodel;

import intive.ideabox.model.FirebaseProvider;
import intive.ideabox.utility.NavigationUtils;

public class AddIdeaViewModel {

    private static final int MIN_IDEA_TEXT_LENGTH = 3;
    private android.content.Context _context;


    public AddIdeaViewModel(android.content.Context context) {
            _context = context;
    }

    public void saveIdea(String userIdea) {
        if (userIdea.length() >= MIN_IDEA_TEXT_LENGTH) {
            FirebaseProvider dataProvider = FirebaseProvider.getInstance();
            dataProvider.saveIdea(userIdea);
            NavigationUtils.getInstance().setIdeaListState(true);
        }else
            android.widget.Toast.makeText(_context, intive.ideabox.R.string.to_short_idea,
                    android.widget.Toast.LENGTH_SHORT).show();
    }
}
