package intive.ideabox.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import intive.ideabox.R;
import intive.ideabox.databinding.FragmentAddIdeaBinding;
import intive.ideabox.viewmodel.AddIdeaViewModel;

public class AddIdeaFragment extends android.support.v4.app.Fragment {

    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        FragmentAddIdeaBinding binding= DataBindingUtil.inflate(inflater, R.layout.fragment_add_idea, container, false);
        String idea = "";
        AddIdeaViewModel viewModel = new AddIdeaViewModel(getActivity());
        binding.setIdeaViewModel(viewModel);
        binding.setIdea(idea);
        viewModel.setupParent(binding.getRoot());
        return binding.getRoot();

    }

}