package intive.ideabox;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.Observable;
import java.util.Observer;

import intive.ideabox.databinding.FragmentViewBinding;
import intive.ideabox.viewmodels.IdeaViewModel;

public class MainActivity extends FragmentActivity implements Observer{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        FragmentViewBinding binding = DataBindingUtil.setContentView(this, R.layout.fragment_view);
        IdeaViewModel vm = ViewModelProviders.of(this).get(IdeaViewModel.class);
        binding.setViewModel(vm);
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
