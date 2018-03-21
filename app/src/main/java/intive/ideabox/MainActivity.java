package intive.ideabox;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Observable;
import java.util.Observer;

import intive.ideabox.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements Observer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        intive.ideabox.IdeaViewModel ideaViewModel = new intive.ideabox.IdeaViewModel(new intive.ideabox.IdeaModel(false));
        activityBinding.setIdeaViewModel(ideaViewModel);
        activityBinding.executePendingBindings();
    }


    @Override
    public void update(Observable observable, Object o) {

    }
}
