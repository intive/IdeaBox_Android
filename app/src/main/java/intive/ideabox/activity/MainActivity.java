package intive.ideabox.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import intive.ideabox.R;
import intive.ideabox.authentication.UserAuthenticate;
import intive.ideabox.fragment.AddIdeaFragment;
import intive.ideabox.fragment.AuthenticationFragment;
import intive.ideabox.fragment.DetailIdeaFragment;
import intive.ideabox.fragment.IdeaListFragment;
import intive.ideabox.fragment.QuitAddIdeaDialogFragment;
import intive.ideabox.utility.FragmentState;
import intive.ideabox.utility.NavigationUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.app_bar));

        setFragmentStatusObserver();
        setSnackBarObserver();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.appbar_item_login) {
            NavigationUtils.getInstance().setState(FragmentState.UserAuthenticate);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setSnackBarObserver() {
        NavigationUtils.getInstance().getSnackBar().observe(this, s -> {
            showSnackBar(s);
        });
    }

    private void setFragmentStatusObserver() {
        NavigationUtils.getInstance().getState().observe(this, state -> {
            setFragment(state);
        });
    }

    private void setFragment(FragmentState state) {
        switch (state) {
            case IdeaList:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, IdeaListFragment.newInstance(), "ideaListFragment")
                        .commit();
                break;
            case AddIdea:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, AddIdeaFragment.newInstance(), "addIdeaFragment")
                        .commit();
                break;
            case DetailIdea:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, DetailIdeaFragment.newInstance(), "detailIdeaFragment")
                        .commit();
                break;
            case UserAuthenticate:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, AuthenticationFragment.newInstance(), "authenticationFragment")
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }

    private void showSnackBar(int text) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.main_activity_layout), text, Snackbar.LENGTH_LONG);
        snackbar.show();
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
