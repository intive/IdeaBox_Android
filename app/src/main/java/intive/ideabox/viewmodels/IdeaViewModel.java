package intive.ideabox.viewmodels;

import intive.ideabox.models.IdeaModel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

public class IdeaViewModel extends AndroidViewModel {
    public IdeaModel idea;
    public ObservableBoolean isIdea;

//    public IdeaViewModel() {
//
//        this.idea = new IdeaModel(false);
//        isIdea= new ObservableBoolean();
//        isIdea.set(false);
//    }

    public IdeaViewModel(@NonNull Application application) {
        super(application);
        this.idea = new IdeaModel(false);
        isIdea= new ObservableBoolean();
        isIdea.set(false);
    }
}
