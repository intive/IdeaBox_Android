package intive.ideabox;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import intive.ideabox.viewmodels.IdeaViewModel;

public class MainActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        IdeaViewModel vm = ViewModelProviders.of(this, new IdeaViewModel.Factory(getApplication())).get(IdeaViewModel.class);
    }
}
