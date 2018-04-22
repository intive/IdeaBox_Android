package intive.ideabox.viewmodel;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.arch.lifecycle.ViewModel;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import intive.ideabox.R;
import intive.ideabox.fragment.IdeaListFragment;


public class QuitAddIdeaViewModel extends ViewModel{

    @SuppressLint("StaticFieldLeak")
    private FragmentActivity fragmentActivity;
    private Dialog dialog;

    public QuitAddIdeaViewModel(FragmentActivity fragmentActivity, Dialog dialogFromAddScreen) {
        this.fragmentActivity = fragmentActivity;
        dialog = dialogFromAddScreen;
    }
    public void positiveHandling(){
        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, IdeaListFragment.newInstance(false)).commit();
        Log.d("Yes","Yes pressed.");
        dialog.dismiss();

    }
    public void negativeHandling(){
        //code will be added here which handles  flow of "No" button
        Log.d("No","No pressed.");
        dialog.dismiss();
    }
}
