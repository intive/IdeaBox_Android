package intive.ideabox.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import intive.ideabox.R;
import intive.ideabox.fragment.AddIdeaFragment;
import intive.ideabox.fragment.IdeaListFragment;
import intive.ideabox.fragment.QuitAddIdeaDialogFragment;
import intive.ideabox.utility.FragmentState;
import intive.ideabox.utility.NavigationUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationUtils.getInstance().getState().observe(this, state -> {
            setFragment(state);
        });
    }

    private void setFragment(FragmentState state) {
        switch (state) {
            case IdeaList:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer,
                                IdeaListFragment.newInstance(NavigationUtils.getInstance().getIdeaListSnackBarState()),
                                "ideaListFragment")
                        .commit();
                break;
            case AddIdea:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer,
                                AddIdeaFragment.newInstance(),
                                "addIdeaFragment")
                        .commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Fragment addIdeaFragment = getSupportFragmentManager().findFragmentByTag(getString(R.string.add_idea_tag));
        if (addIdeaFragment != null && addIdeaFragment.isVisible()) {
            QuitAddIdeaDialogFragment dialog = new QuitAddIdeaDialogFragment();
            dialog.show(this.getFragmentManager(), getString(R.string.dialog_tag));
        } else {
            super.onBackPressed();
        }
    }

}
