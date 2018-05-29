package intive.ideabox.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.Objects;

import intive.ideabox.R;
import intive.ideabox.databinding.FragmentDetailIdeaBinding;
import intive.ideabox.model.IdeaData;
import intive.ideabox.viewmodel.DetailIdeaViewModel;

public class DetailIdeaFragment extends Fragment {

    public static DetailIdeaFragment newInstance() {
        DetailIdeaFragment fragment = new DetailIdeaFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDetailIdeaBinding detailIdeaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_idea, container, false);
        IdeaData editedIdea = DetailIdeaViewModel.choosenIdea.get();
        detailIdeaBinding.setObject(editedIdea);
        detailIdeaBinding.setViewModel(new DetailIdeaViewModel());
        DetailIdeaViewModel.getStatuses().observe(this, statuses -> {
            detailIdeaBinding.setSpinAdapter(new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_dropdown_item, Objects.requireNonNull(statuses)));


        });

        return detailIdeaBinding.getRoot();
    }
}
