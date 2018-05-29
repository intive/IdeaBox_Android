package intive.ideabox.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;

import java.util.List;

import intive.ideabox.R;
import intive.ideabox.authentication.FirebaseUserAuthenticate;
import intive.ideabox.model.FirebaseProvider;
import intive.ideabox.model.IdeaData;
import intive.ideabox.utility.FragmentState;
import intive.ideabox.utility.NavigationUtils;

public class DetailIdeaViewModel extends ViewModel {
    public static ObservableField<IdeaData> choosenIdea = new ObservableField<>();
    public static MutableLiveData<List<String>> statuses = new MutableLiveData<>();
    private ObservableField<String> ideaStatus = new ObservableField<>();
    private ObservableField<Boolean> isUserAdmin = new ObservableField<>();

    public DetailIdeaViewModel() {
        this.isUserAdmin.set(getUserRole());
    }

    public static MutableLiveData<List<String>> getStatuses() {
        return statuses;
    }


    public ObservableField<String> getIdeaStatus() {
        ideaStatus.set(choosenIdea.get().getIdeaStatus());
        return ideaStatus;
    }

    public void setIdeaStatus(String status) {
        ideaStatus.set(choosenIdea.get().getIdeaStatus());

    }

    public boolean getUserRole() {
        return FirebaseUserAuthenticate.isUserAdmin;
    }

    public IdeaData getChoosenIdea() {
        return choosenIdea.get();
    }

    public void editIdea(IdeaData choosenIdea) {
        FirebaseProvider dataProvider = FirebaseProvider.getInstance();
        choosenIdea.setIdeaStatus(getIdeaStatus().get());
        Log.d("awadw",choosenIdea.getIdeaStatus());
        Log.d("awadw",choosenIdea.getIdeaUser());
        Log.d("awadw",String.valueOf(choosenIdea.getIdeaTime()));
        boolean getEdited = dataProvider.editIdea(choosenIdea);
        NavigationUtils.getInstance().setSnackBar(R.string.added_idea);
        NavigationUtils.getInstance().setState(FragmentState.IdeaList);

    }
}
