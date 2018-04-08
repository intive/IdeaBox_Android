package intive.ideabox.viewmodel;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import intive.ideabox.model.IdeaData;

public class IdeaListViewModel extends ViewModel {

    public IdeaListViewModel() {

    }

    public List<IdeaData> LoadIdeaData() {
        //Implement getting data from data provider
        return null;
    }

    public void onFabClick() {
        //Implement changing fragments
    }
}
