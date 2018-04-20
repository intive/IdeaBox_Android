package intive.ideabox.viewmodel;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.arch.lifecycle.ViewModel;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


public class QuitAddIdeaViewModel extends ViewModel{

    @SuppressLint("StaticFieldLeak")
    private FragmentActivity mFragmentActivity;
    private Dialog dialog;

    public QuitAddIdeaViewModel(FragmentActivity fragmentActivity, Dialog dialogFromAddScreen) {
        mFragmentActivity = fragmentActivity;
        dialog = dialogFromAddScreen;
    }
    public void positiveHandling(){
        //code will be added here which handles  flow of "Yes" button
        Log.d("Yes","Yes pressed.");
        dialog.dismiss();

    }
    public void negativeHandling(){
        //code will be added here which handles  flow of "No" button
        Log.d("No","No pressed.");
        dialog.dismiss();
    }
}
