package intive.ideabox.fragment;

/**
 * Created by Pc on 09.04.2018.
 */

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import intive.ideabox.R;
import intive.ideabox.activity.MainActivity;
import intive.ideabox.databinding.FragmentAddIdeaBinding;
import intive.ideabox.model.IdeaData;
import intive.ideabox.viewmodel.AddIdeaViewModel;

public class AddIdeaFragment extends android.support.v4.app.Fragment {

    public FragmentActivity mainContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        FragmentAddIdeaBinding binding= DataBindingUtil.inflate(inflater, R.layout.fragment_add_idea, container, false);
        IdeaData ideaData = new IdeaData("default idea text", "defUser");
        AddIdeaViewModel viewModel = new AddIdeaViewModel();
        binding.setIdeaViewModel(viewModel);
        binding.setUserIdea(ideaData);
        binding.setMainContext(mainContext);
        return binding.getRoot();

    }
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        mainContext=(MainActivity) activity;
        super.onAttach(activity);
    }
}