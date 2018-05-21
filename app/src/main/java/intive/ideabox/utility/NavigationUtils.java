package intive.ideabox.utility;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

public class NavigationUtils {

    private MutableLiveData<FragmentState> state;

    private static NavigationUtils instance = null;

    private NavigationUtils() {
        state = new MutableLiveData<>();
        state.setValue(FragmentState.UserAuthenticate);
    }

    public synchronized static NavigationUtils getInstance() {
        if (instance == null) {
            instance = new NavigationUtils();
        }
        return instance;
    }

    public void setState(FragmentState state) {
        this.state.setValue(state);
    }

    public LiveData<FragmentState> getState() {
        return state;
    }
}
