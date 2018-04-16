package intive.ideabox.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


public class QuitAddIdeaViewModel extends ViewModel{

    private FragmentActivity mFragmentActivity;

    public QuitAddIdeaViewModel(FragmentActivity fragmentActivity) {
        mFragmentActivity = fragmentActivity;
    }
    public void positiveHandling(){
        //code will be added here which handles  flow of "Yes" button
        Log.d("Yes","Yes pressed.");
    }
    public void negativeHandling(){
        //code will be added here which handles  flow of "No" button
        Log.d("No","No pressed.");
    }
}
