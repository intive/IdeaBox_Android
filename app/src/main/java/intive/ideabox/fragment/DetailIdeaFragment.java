package intive.ideabox.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import intive.ideabox.R;
import intive.ideabox.databinding.FragmentDetailIdeaBinding;
import intive.ideabox.viewmodel.DetailIdeaViewModel;

public class DetailIdeaFragment extends Fragment {

    public static DetailIdeaFragment newInstance() {
        DetailIdeaFragment fragment = new DetailIdeaFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDetailIdeaBinding detailIdeaBinding = DataBindingUtil.inflate(inflater ,R.layout.fragment_detail_idea, container, false);
        detailIdeaBinding.setViewModel(new DetailIdeaViewModel());


        return detailIdeaBinding.getRoot();
    }
}
