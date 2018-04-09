package intive.ideabox.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.Observable;
import java.util.Observer;

import intive.ideabox.R;

public class MainActivity extends FragmentActivity implements Observer{
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            // will be conntected with idea_list fragment
            //IdeaListFragment.setShowSnackBarFlag(true);
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new IdeaListFragment()).commit();
        }

    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
