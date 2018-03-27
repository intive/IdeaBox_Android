package intive.ideabox.models;

import android.app.Fragment;
import ideabox.intive.viewmodels.IdeaViewModel;

import android.os.Bundle;

import java.util.Observable;
import java.util.Observer;

public class View extends Fragment implements Observer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IdeaViewModel vm = ViewModelProviders.of(this, new IdeaViewModel.Factory(getApplication())).get(IdeaViewModel.class);

    }


    @Override
    public void update(Observable observable, Object o) {

    }
}
