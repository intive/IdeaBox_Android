package intive.ideabox.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;


import java.util.List;

import intive.ideabox.model.FirebaseProvider;
import intive.ideabox.model.IdeaData;
import intive.ideabox.utility.NavigationUtils;

public class IdeaListViewModel extends ViewModel {

    public IdeaListViewModel() {}

    public LiveData<List<IdeaData>> getIdeas() {
        return FirebaseProvider.getInstance().getIdeas();
    }

    public void onFabClick() {
        NavigationUtils.getInstance().setAddIdeaState();
    }
}
