package intive.ideabox.fragment;

import android.databinding.DataBindingUtil;
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

public class IdeaListFragment extends Fragment {

    public static IdeaListFragment newInstance(boolean showSnackBar) {
        IdeaListFragment fragment = new IdeaListFragment();
        Bundle args = new Bundle();
        args.putBoolean("KEY_SHOULD_SHOW_SNACK", showSnackBar);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = getArguments();
        boolean showSnackBar = args.getBoolean("KEY_SHOULD_SHOW_SNACK", false);

        FragmentIdeaListBinding fragmentIdeaListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_idea_list, container, false);
        IdeaListViewModel ideaListViewModel = new IdeaListViewModel(getActivity());
        fragmentIdeaListBinding.setViewModel(ideaListViewModel);

        RecyclerView mRecyclerView = fragmentIdeaListBinding.ideaRecycler;

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        IdeaListAdapter mIdeaListAdapter = new IdeaListAdapter(R.layout.row_idea_list);
        mRecyclerView.setAdapter(mIdeaListAdapter);

        mIdeaListAdapter.setData(ideaListViewModel.LoadIdeaData());

        if (showSnackBar)
            showSnackBar(fragmentIdeaListBinding.getRoot());

        return fragmentIdeaListBinding.getRoot();
    }

    private void showSnackBar(View view) {
        Snackbar snackbar = Snackbar.make(view, R.string.added_idea, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
