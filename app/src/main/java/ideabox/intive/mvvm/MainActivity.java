package ideabox.intive.mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Observable;
import java.util.Observer;

import ideabox.intive.mvvm.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements Observer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        IdeaViewModel ideaViewModel = new IdeaViewModel(false);
        activityBinding.setIdeaViewModel(ideaViewModel);
        ideaViewModel.addObserver(this);
    }


    @Override
    public void update(Observable observable, Object o) {

    }
}
