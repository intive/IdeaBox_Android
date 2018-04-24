package intive.ideabox.utility;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

public class NavigationUtils {

    private boolean ideaListSnackBarState;

    private MutableLiveData<FragmentState> state;

    private static NavigationUtils instance = null;

    private NavigationUtils() {
        state = new MutableLiveData<>();
        state.setValue(FragmentState.IdeaList);
        ideaListSnackBarState = false;
    }

    public synchronized static NavigationUtils getInstance() {
        if (instance == null) {
            instance = new NavigationUtils();
        }
        return instance;
    }

    public void setIdeaListState(boolean ideaListSnackBarState) {
        this.ideaListSnackBarState = ideaListSnackBarState;
        state.setValue(FragmentState.IdeaList);
    }

    public void setAddIdeaState() {
        state.setValue(FragmentState.AddIdea);
    }

    public LiveData<FragmentState> getState() {
        return state;
    }

    public boolean getIdeaListSnackBarState() {
        return ideaListSnackBarState;
    }
}
