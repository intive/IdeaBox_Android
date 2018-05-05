package intive.ideabox.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import intive.ideabox.R;
import intive.ideabox.adapter.IdeaListAdapter;
import intive.ideabox.databinding.FragmentIdeaListBinding;
import intive.ideabox.model.AdapterListener;
import intive.ideabox.model.IdeaData;
import intive.ideabox.viewmodel.IdeaListViewModel;

public class IdeaListFragment extends Fragment implements AdapterListener{

    private static final String SNACK_BAR_KEY = "KEY_SHOULD_SHOW_SNACK";
    RecyclerView recyclerView;
    private IdeaListAdapter ideaListAdapter;
    IdeaListViewModel ideaListViewModel;
    static int a;
    public static IdeaListFragment newInstance(boolean showSnackBar) {
        IdeaListFragment fragment = new IdeaListFragment();
        Bundle args = new Bundle();
        args.putBoolean(SNACK_BAR_KEY, showSnackBar);
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
        boolean showSnackBar = args.getBoolean(SNACK_BAR_KEY, false);

        FragmentIdeaListBinding fragmentIdeaListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_idea_list, container, false);
        ideaListViewModel = new IdeaListViewModel();
        fragmentIdeaListBinding.setViewModel(ideaListViewModel);

        recyclerView = fragmentIdeaListBinding.ideaRecycler;

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        ideaListAdapter = new IdeaListAdapter(R.layout.row_idea_list);
        ideaListAdapter.setAdapterListener(this);
        recyclerView.setAdapter(ideaListAdapter);
        ideaListViewModel.getIdeas().observe(this, ideas -> {
            ideaListAdapter.setData(ideas);
        });
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());
                if (child != null ) {
                    a = recyclerView.getChildAdapterPosition(child);
                    return false;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }});
        if (showSnackBar)
            showSnackBar(fragmentIdeaListBinding.getRoot());

        return fragmentIdeaListBinding.getRoot();
    }

    private void showSnackBar(View view) {
        Snackbar snackbar = Snackbar.make(view, R.string.added_idea, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onItemClick(View childView) {
        IdeaData ideaData = (IdeaData)ideaListAdapter.getObjForPosition(a);
        ideaListViewModel.addVote(ideaData);
    }
}
