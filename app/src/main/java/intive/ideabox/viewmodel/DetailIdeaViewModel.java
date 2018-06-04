package intive.ideabox.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import java.util.List;

import javax.inject.Inject;

import intive.ideabox.IdeaBoxApplication;
import intive.ideabox.R;
import intive.ideabox.authentication.FirebaseUserAuthenticate;
import intive.ideabox.authentication.UserAuthenticate;
import intive.ideabox.model.FirebaseProvider;
import intive.ideabox.model.IdeaData;
import intive.ideabox.utility.FragmentState;
import intive.ideabox.utility.NavigationUtils;

public class DetailIdeaViewModel extends ViewModel {
    public static ObservableField<IdeaData> choosenIdea = new ObservableField<>();
    public static MutableLiveData<List<String>> statuses = new MutableLiveData<>();
    private ObservableField<String> ideaStatus = new ObservableField<>();
    private ObservableField<Boolean> isUserAdmin = new ObservableField<>();

    @Inject
    UserAuthenticate userAuthenticate;

    public DetailIdeaViewModel() {
        IdeaBoxApplication.applicationComponent.inject(this);

        this.isUserAdmin.set(getUserRole());
    }

    public static MutableLiveData<List<String>> getStatuses() {
        return statuses;
    }
    public MutableLiveData<IdeaData> currentIdea = new MutableLiveData<>();
    public ObservableField<String> getIdeaStatus() {
        ideaStatus.set(choosenIdea.get().getIdeaStatus());
        return ideaStatus;
    }

    public void setIdeaStatus(String status) {
        ideaStatus.set(choosenIdea.get().getIdeaStatus());
    }

    public MutableLiveData<IdeaData> getCurrentIdea(String ideaUser, long ideaTime) {
        return FirebaseProvider.getInstance().getCurrentIdea(ideaUser,ideaTime);
    }

    public boolean getUserRole() {
        return userAuthenticate.getUserStatus();
    }

    public IdeaData getChoosenIdea() {
        return choosenIdea.get();
    }

    public void editIdea(IdeaData choosenIdea, String ideaStatus) {
        FirebaseProvider dataProvider = FirebaseProvider.getInstance();
        choosenIdea.setIdeaStatus(ideaStatus);
        dataProvider.editIdea(choosenIdea);
        NavigationUtils.getInstance().setSnackBar(R.string.edited_idea);
        NavigationUtils.getInstance().setState(FragmentState.IdeaList);

    }
}
