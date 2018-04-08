package intive.ideabox.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import intive.ideabox.R;
import intive.ideabox.adapter.IdeaListAdapter;
import intive.ideabox.databinding.FragmentIdeaListBinding;
import intive.ideabox.viewmodel.IdeaListViewModel;

public class IdeaListFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private IdeaListAdapter mIdeaListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public IdeaListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_idea_list, container, false);

        FragmentIdeaListBinding fragmentIdeaListBinding = FragmentIdeaListBinding.bind(view);
        IdeaListViewModel ideaListViewModel = new IdeaListViewModel();
        fragmentIdeaListBinding.setViewModel(ideaListViewModel);

        mRecyclerView = view.findViewById(R.id.idea_recycler);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        mIdeaListAdapter = new IdeaListAdapter(ideaListViewModel.LoadIdeaData());
        mRecyclerView.setAdapter(mIdeaListAdapter);

        return fragmentIdeaListBinding.getRoot();
    }
}
