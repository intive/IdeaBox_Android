package intive.ideabox.fragment;

import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import intive.ideabox.R;
import intive.ideabox.adapter.IdeaListAdapter;
import intive.ideabox.databinding.FragmentIdeaListBinding;
import intive.ideabox.model.NetworkStatus;
import intive.ideabox.utility.RxBroadcastReceiver;
import intive.ideabox.viewmodel.IdeaListViewModel;
import io.reactivex.disposables.Disposable;

public class IdeaListFragment extends Fragment {

    Disposable disposable;
    NetworkStatus status = new NetworkStatus();
    FragmentIdeaListBinding fragmentIdeaListBinding;
    private IdeaListAdapter ideaListAdapter;

    public static IdeaListFragment newInstance() {
        IdeaListFragment fragment = new IdeaListFragment();
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        disposable.dispose();
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION);
        disposable = RxBroadcastReceiver.create(getActivity(), intentFilter).share()
                .subscribe(data -> {
                    if (RxBroadcastReceiver.isConnection(getActivity())) {
                        status.isConnected.set(true);
                        fragmentIdeaListBinding.getRoot().findViewById(R.id.add_fab).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));


                    } else {
                        showConnectionSnackBar(fragmentIdeaListBinding.getRoot());
                        status.isConnected.set(false);
                        fragmentIdeaListBinding.getRoot().findViewById(R.id.add_fab).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorDisconnected)));
                    }

                });


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        fragmentIdeaListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_idea_list, container, false);
        IdeaListViewModel ideaListViewModel = new IdeaListViewModel();
        fragmentIdeaListBinding.setViewModel(ideaListViewModel);
        fragmentIdeaListBinding.setStatus(status);
        RecyclerView recyclerView = fragmentIdeaListBinding.ideaRecycler;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        ideaListAdapter = new IdeaListAdapter(R.layout.row_idea_list);
        recyclerView.setAdapter(ideaListAdapter);
        ideaListViewModel.getIdeas().observe(this, ideas -> ideaListAdapter.setData(ideas));

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        return fragmentIdeaListBinding.getRoot();
    }

    private void showConnectionSnackBar(View view) {
        if (view != null) {
            Snackbar snackbar = Snackbar.make(view, R.string.connection_lost_message, Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }
}
