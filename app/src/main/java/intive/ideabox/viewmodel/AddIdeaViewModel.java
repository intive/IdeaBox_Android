package intive.ideabox.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import intive.ideabox.model.FirebaseProvider;
import intive.ideabox.utility.NavigationUtils;

public class AddIdeaViewModel  extends BaseObservable{

    private static final int MIN_IDEA_TEXT_LENGTH = 3;
    private static Integer TOTAL_IDEA_LENGTH = 256;
    public ObservableField<Integer> charactersRemaining = new ObservableField<>(TOTAL_IDEA_LENGTH);
    public ObservableField<String> idea = new ObservableField<>("");
    public AddIdeaViewModel(String idea) {
        this.idea.set(idea);
    }


    public void saveIdea(View view, ObservableField<String> userIdea) {
        if (userIdea.get().length() >= MIN_IDEA_TEXT_LENGTH) {
            FirebaseProvider dataProvider = FirebaseProvider.getInstance();
            dataProvider.saveIdea(userIdea.get());
            NavigationUtils.getInstance().setIdeaListState(true);
        }else
            android.widget.Toast.makeText( view.getContext(), intive.ideabox.R.string.to_short_idea,
                    android.widget.Toast.LENGTH_SHORT).show();
    }
    public TextWatcher ideaTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            charactersRemaining.set(TOTAL_IDEA_LENGTH - editable.toString().length());
        }
    };
}
