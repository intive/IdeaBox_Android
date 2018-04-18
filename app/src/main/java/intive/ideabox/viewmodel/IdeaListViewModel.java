package intive.ideabox.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.app.FragmentActivity;

import java.util.List;

import intive.ideabox.R;
import intive.ideabox.fragment.AddIdeaFragment;
import intive.ideabox.model.FirebaseProvider;
import intive.ideabox.model.IdeaData;

public class IdeaListViewModel extends ViewModel {

    private FragmentActivity fragmentActivity;

    public IdeaListViewModel(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    public LiveData<List<IdeaData>> getIdeas(){
        return FirebaseProvider.getInstance().getIdeas();
    }

    public void onFabClick() {
        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new AddIdeaFragment()).addToBackStack(null).commit();
    }
}
