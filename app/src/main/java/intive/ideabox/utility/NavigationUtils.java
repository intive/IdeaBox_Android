package intive.ideabox.utility;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

public class NavigationUtils {

    private MutableLiveData<FragmentState> state;
    private MutableLiveData<Integer> snackBarText;

    private static NavigationUtils instance = null;

    private NavigationUtils() {
        state = new MutableLiveData<>();
        snackBarText = new MutableLiveData<>();

        state.setValue(FragmentState.IdeaList);
    }

    public synchronized static NavigationUtils getInstance() {
        if (instance == null) {
            instance = new NavigationUtils();
        }
        return instance;
    }

    public void setSnackBar(int text) {
        this.snackBarText.setValue(text);
    }

    public void setState(FragmentState state) {
        this.state.setValue(state);
    }

    public LiveData<FragmentState> getState() {
        return state;
    }

    public LiveData<Integer> getSnackBar() {
        return snackBarText;
    }
}
