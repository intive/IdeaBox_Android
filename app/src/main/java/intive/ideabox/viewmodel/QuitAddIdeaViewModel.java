package intive.ideabox.viewmodel;

import android.app.Dialog;
import android.arch.lifecycle.ViewModel;

import android.util.Log;

import intive.ideabox.utility.NavigationUtils;

public class QuitAddIdeaViewModel extends ViewModel {

    private Dialog dialog;

    public QuitAddIdeaViewModel(Dialog dialogFromAddScreen) {
        dialog = dialogFromAddScreen;
    }

    public void positiveHandling() {
        NavigationUtils.getInstance().setIdeaListState(false);
        Log.d("Yes", "Yes pressed.");
        dialog.dismiss();
    }

    public void negativeHandling() {
        //code will be added here which handles  flow of "No" button
        Log.d("No", "No pressed.");
        dialog.dismiss();
    }
}
