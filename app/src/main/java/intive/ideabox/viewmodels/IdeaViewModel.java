package intive.ideabox.viewmodels;

import intive.ideabox.models.IdeaModel;


import android.databinding.ObservableBoolean;

public class IdeaViewModel extends AndroidViewModel {
    public IdeaModel idea;
    public ObservableBoolean isIdea;

    public IdeaViewModel() {
        this.idea = new IdeaModel(false);
        isIdea= new ObservableBoolean();
        isIdea.set(false);
    }

}
