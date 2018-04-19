package intive.ideabox.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import intive.ideabox.R;
import intive.ideabox.fragment.IdeaListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(null == savedInstanceState)
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, IdeaListFragment.newInstance(false)).commit();
    }

}
