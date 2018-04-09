package intive.ideabox.viewmodel;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import intive.ideabox.model.IdeaData;

public class IdeaListViewModel extends ViewModel {

    public IdeaListViewModel() {

    }

    public List<IdeaData> LoadIdeaData() {
        List<IdeaData> list = new ArrayList<>();
        list.add(new IdeaData("testk", "dsa"));
        return list;
    }

    public void onFabClick() {

    }

    public void showSnackBar(){
        //Implement showing snackbar
    }
}
