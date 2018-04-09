package intive.ideabox.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import intive.ideabox.R;
import intive.ideabox.model.IdeaData;

public class IdeaListViewModel extends ViewModel {

    private FragmentActivity fragmentActivity;

    public IdeaListViewModel(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    public List<IdeaData> LoadIdeaData() {
        List<IdeaData> list = new ArrayList<>();
        list.add(new IdeaData("testk", "dsa"));
        return list;
    }

    public void onFabClick() {
        //Todo: change to add idea fragment after integration
        //fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new BlankFragment()).commit();
    }
}
