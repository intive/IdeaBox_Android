package intive.ideabox.viewmodel;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import intive.ideabox.model.DataProvider;
import intive.ideabox.model.IdeaData;

public class IdeaListViewModel extends ViewModel {

    public IdeaListViewModel() {

    }

    public List<IdeaData> LoadIdeaData() {
        DataProvider provider = DataProvider.getInstance();
        List<IdeaData> list = provider.loadIdea();
        return list;
    }

    public void onFabClick() {
        //Implement changing fragments
    }

    public void showSnackBar(){
        //Implement showing snackbar
    }
}
