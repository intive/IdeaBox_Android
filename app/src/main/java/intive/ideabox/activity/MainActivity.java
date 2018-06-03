package intive.ideabox.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import intive.ideabox.R;
import intive.ideabox.fragment.AddIdeaFragment;
import intive.ideabox.fragment.AuthenticationFragment;
import intive.ideabox.fragment.DetailIdeaFragment;
import intive.ideabox.fragment.IdeaListFragment;
import intive.ideabox.fragment.QuitAddIdeaDialogFragment;
import intive.ideabox.utility.FragmentState;
import intive.ideabox.utility.NavigationUtils;
import intive.ideabox.utility.UserDataUtils;

import static intive.ideabox.utility.UserDataUtils.getHashedData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.app_bar));
        UserDataUtils.setUserAndCheckPermissions(this);
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
        Fragment detailIdeaFragment = getSupportFragmentManager().findFragmentByTag("detailIdeaFragment");
        Fragment authFragment = getSupportFragmentManager().findFragmentByTag("authenticationFragment");
        if (addIdeaFragment != null && addIdeaFragment.isVisible()) {
            QuitAddIdeaDialogFragment dialog = new QuitAddIdeaDialogFragment();
            dialog.show(this.getFragmentManager(), getString(R.string.dialog_tag));
        } else if (detailIdeaFragment != null && detailIdeaFragment.isVisible()) {
            NavigationUtils.getInstance().setState(FragmentState.IdeaList);
        }
        else if (authFragment != null && authFragment.isVisible()) {
            NavigationUtils.getInstance().setState(FragmentState.IdeaList);
        }

        else {
            super.onBackPressed();
        }

    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (UserDataUtils.MY_PERMISSIONS_REQUEST_READ_CONTACTS) {

            case 2: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), R.string.permission_granted, Toast.LENGTH_SHORT).show();
                    UserDataUtils.isPermissionGranted.setValue(true);
                    final TelephonyManager ts = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
                    UserDataUtils.imeiUser = getHashedData(ts.getDeviceId());
                } else {
                    Toast.makeText(getApplicationContext(), R.string.permission_denied, Toast.LENGTH_SHORT).show();
                }

            }

        }
    }
}