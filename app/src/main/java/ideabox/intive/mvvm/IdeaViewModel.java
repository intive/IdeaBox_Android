package ideabox.intive.mvvm;

import android.databinding.ObservableBoolean;

import java.util.Observable;

/**
 * Created by Artur on 16.03.2018.
 * ViewModel for application
 */

public class IdeaViewModel extends Observable {

    public IdeaModel Idea;
    public boolean id;
    ObservableBoolean ideaItem;

    public IdeaViewModel(boolean idea) {
        this.Idea = new IdeaModel(idea);
        this.ideaItem = new ObservableBoolean(idea);
        this.id=idea;

    }
}
