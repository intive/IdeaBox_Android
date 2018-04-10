package intive.ideabox.viewmodel;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import intive.ideabox.R;
import intive.ideabox.fragment.IdeaListFragment;

public class AddIdeaViewModel {

    FragmentActivity mFragmentActivity;

    public AddIdeaViewModel(FragmentActivity fragmentActivity) {
        mFragmentActivity = fragmentActivity;
    }

    public void saveIdea(String userIdea) {
        if (userIdea.length() < 3) {
            Toast.makeText(mFragmentActivity.getApplicationContext(), R.string.to_short_idea, Toast.LENGTH_SHORT).show();
        }
        else if (userIdea.length() > 256) {
            Toast.makeText(mFragmentActivity.getApplicationContext(), R.string.to_long_idea, Toast.LENGTH_SHORT).show();
        } else {
            // Todo: add sending data to firebase mechanism;
            //DataProvider dataProvider = DataProvider.getInstance();
            //dataProvider.saveIdea(userIdea);
            mFragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, IdeaListFragment.newInstance(true)).commit();
        }
    }
}