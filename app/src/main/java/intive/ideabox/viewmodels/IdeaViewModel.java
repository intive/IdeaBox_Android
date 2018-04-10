package intive.ideabox.viewmodels;

import intive.ideabox.models.IdeaModel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

public class IdeaViewModel extends AndroidViewModel implements Observable{
    public IdeaModel idea;
    public ObservableBoolean isIdea;


    public IdeaViewModel(@NonNull Application application) {
        super(application);
        this.idea = new IdeaModel(false);
        isIdea= new ObservableBoolean();
        isIdea.set(false);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
