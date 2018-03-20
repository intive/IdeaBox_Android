package ideabox.intive.mvvm;

import android.databinding.ObservableBoolean;

/**
 * Created by Artur on 16.03.2018.
 * ViewModel for application
 */

public class IdeaViewModel {
    public ObservableBoolean isIdea = new ObservableBoolean();

    public IdeaViewModel(IdeaModel idea) {
        isIdea.set(idea.getIdeaItem());
    }
}
