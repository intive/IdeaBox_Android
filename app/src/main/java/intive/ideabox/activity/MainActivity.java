package intive.ideabox.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import intive.ideabox.R;
import intive.ideabox.fragment.AddIdeaFragment;
import intive.ideabox.fragment.DetailIdeaFragment;
import intive.ideabox.fragment.IdeaListFragment;
import intive.ideabox.fragment.QuitAddIdeaDialogFragment;
import intive.ideabox.utility.FragmentState;
import intive.ideabox.utility.NavigationUtils;

public class MainActivity extends AppCompatActivity {

    private static final String CURRENT_FRAGMENT = "CURRENT_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            setFragmentStatusObserver(savedInstanceState.getString(CURRENT_FRAGMENT));
        } else {
            setFragmentStatusObserver(null);
        }

        //setSnackBarObserver();
    }

   /* private void setSnackBarObserver() {
        NavigationUtils.getInstance().getSnackBar().observe(this, s -> {
            showSnackBar(s);
        });
    }
*/
    private void setFragmentStatusObserver(String lastUsedState) {
        NavigationUtils.getInstance().getState().observe(this, state -> {

            if (state.toString().equals(lastUsedState))
                setFragment(state);
        });
    }

    private void setFragment(FragmentState state) {
        switch (state) {
            case IdeaList:
                changeFragment(IdeaListFragment.newInstance(true), "ideaListFragment");
                break;
            case AddIdea:
                changeFragment(AddIdeaFragment.newInstance(), "addIdeaFragment");
                break;

           /* case DetailIdea:
                changeFragment(DetailIdeaFragment.newInstance(), "detailIdeaFragment");
                break;
            case UserAuthenticate:
                changeFragment(AuthenticationFragment.newInstance(), "authenticationFragment");
                break;
                */

        }
    }

    private void showSnackBar(int text) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.main_activity_layout), text, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void changeFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment, tag).commit();
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(CURRENT_FRAGMENT, NavigationUtils.getInstance().getState().getValue().toString());
    }
}