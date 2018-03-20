package ideabox.intive.mvvm;

import android.databinding.ObservableBoolean;

import java.util.Observable;

/**
 * Created by Artur on 16.03.2018.
 * ViewModel for application
 */

public class IdeaViewModel extends Observable {

    public IdeaModel Idea;
    public ObservableBoolean id;

    public IdeaViewModel(boolean idea) {
        this.Idea = new IdeaModel(idea);
        id = new ObservableBoolean(idea);
    }

    public ObservableBoolean getId() {
        return id;
    }

    public void setId(ObservableBoolean id) {
        this.id = id;
    }
}
