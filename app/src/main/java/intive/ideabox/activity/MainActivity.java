package intive.ideabox.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import intive.ideabox.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // will be conntected with idea_list fragment
        //IdeaListFragment.setShowSnackBarFlag(true);
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new IdeaListFragment()).commit();
    }
}
