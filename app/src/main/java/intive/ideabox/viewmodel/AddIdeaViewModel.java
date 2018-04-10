package intive.ideabox.viewmodel;

import android.content.Context;
import android.widget.Toast;

import intive.ideabox.R;
import intive.ideabox.model.FirebaseProvider;

/**
 * Created by Pc on 09.04.2018.
 */

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
            FirebaseProvider dataProvider = FirebaseProvider.getInstance();
            dataProvider.saveIdea(userIdea);
        }
    }
}