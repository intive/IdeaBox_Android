package intive.ideabox.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import intive.ideabox.R;
import intive.ideabox.fragment.IdeaListFragment;
import intive.ideabox.fragment.QuitAddIdeaDialogFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                IdeaListFragment.newInstance(false)).commit();
    }

    @Override
    public void onBackPressed() {
        Fragment addIdeaFragment = getSupportFragmentManager().findFragmentByTag(getString(R.string.add_idea_tag));
        if (addIdeaFragment != null && addIdeaFragment.isVisible()){
            QuitAddIdeaDialogFragment dialog = new QuitAddIdeaDialogFragment();
            dialog.show(this.getFragmentManager(), "example");
        }else{
            super.onBackPressed();
        }
    }

}
