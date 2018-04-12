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

import java.util.List;

import intive.ideabox.R;
import intive.ideabox.adapter.IdeaListAdapter;
import intive.ideabox.databinding.FragmentIdeaListBinding;
import intive.ideabox.model.FirebaseProvider;
import intive.ideabox.model.IdeaData;
import intive.ideabox.model.OnDataReadyListener;
import intive.ideabox.viewmodel.IdeaListViewModel;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;

public class IdeaListFragment extends Fragment {

    private static final String mSnackBarKey = "KEY_SHOULD_SHOW_SNACK";
    private IdeaListAdapter mIdeaListAdapter;

    public static IdeaListFragment newInstance(boolean showSnackBar) {
        IdeaListFragment fragment = new IdeaListFragment();
        Bundle args = new Bundle();
        args.putBoolean(mSnackBarKey, showSnackBar);
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
        boolean showSnackBar = args.getBoolean(mSnackBarKey, false);

        FragmentIdeaListBinding fragmentIdeaListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_idea_list, container, false);
        IdeaListViewModel ideaListViewModel = new IdeaListViewModel(getActivity());
        fragmentIdeaListBinding.setViewModel(ideaListViewModel);

        RecyclerView mRecyclerView = fragmentIdeaListBinding.ideaRecycler;

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mIdeaListAdapter = new IdeaListAdapter(R.layout.row_idea_list);
        mRecyclerView.setAdapter(mIdeaListAdapter);


        if (showSnackBar)
            showSnackBar(fragmentIdeaListBinding.getRoot());

        return fragmentIdeaListBinding.getRoot();
    }

    private void showSnackBar(View view) {
        Snackbar snackbar = Snackbar.make(view, R.string.added_idea, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseProvider.getInstance().loadIdea(new OnDataReadyListener() {
            @Override
            public void onDataReady(List<IdeaData> ideaData) {
                mIdeaListAdapter.setData(ideaData);
            }

            @Override
            public void onError(String errorMsg) {
                //TODO display error dialog
            }
        });

//TODO or this
//        FirebaseProvider.getInstance()
//                .getIdeas()
//                .toList()
//                .subscribe(new Consumer<List<IdeaData>>() {
//                    @Override
//                    public void accept(List<IdeaData> ideaData) throws Exception {
//                        mIdeaListAdapter.setData(ideaData);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        //TODO display error dialog
//                    }
//                });
    }
}
