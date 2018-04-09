package intive.ideabox.viewmodel;

import android.content.Context;
import android.widget.Toast;

import intive.ideabox.R;


public class AddIdeaViewModel {
    private static final String TAG = "ViewModel tag";

    public  void saveIdea(String userIdea,Context context){
        if(userIdea.length() < 3){
            Toast.makeText(context, R.string.to_short_idea, Toast.LENGTH_SHORT).show();
            return;
        }
        if (userIdea.length() > 256){
            Toast.makeText(context, R.string.to_long_idea, Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            Toast.makeText(context, R.string.idea_proccessing, Toast.LENGTH_SHORT).show();
            // Todo: add sending data to firebase mechanism;
            //DataProvider dataProvider = DataProvider.getInstance();
            //dataProvider.saveIdea(userIdea);
        }


    }
    public void goToMainScreen(Context context){
        return;
        // will be added in making whole project into once
        //try {
          //  FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
            //fragmentManager.beginTransaction().replace(R.id.fragmentContainer,new IdeaListFragment()).commit();
       // } catch (ClassCastException e) {
         //   Log.e(TAG, "Can't get fragment manager");
        //}
    }
}
