package intive.ideabox.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import intive.ideabox.R;
import intive.ideabox.fragment.IdeaListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, IdeaListFragment.newInstance(false)).commit();
    }
}
