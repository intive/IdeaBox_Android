package ideabox.intive.mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        IdeaViewModel ideaViewModel = new IdeaViewModel(false);
        //activityGameBinding.setGameViewModel(gameViewModel);
        ideaViewModel.addObserver(this);
    }


    @Override
    public void update(Observable observable, Object o) {

    }
}
