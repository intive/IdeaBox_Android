package intive.ideabox.fragment;

import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.Objects;

import intive.ideabox.R;
import intive.ideabox.databinding.FragmentDetailIdeaBinding;
import intive.ideabox.model.IdeaData;
import intive.ideabox.model.NetworkStatus;
import intive.ideabox.utility.RxBroadcastReceiver;
import intive.ideabox.viewmodel.DetailIdeaViewModel;
import io.reactivex.disposables.Disposable;

public class DetailIdeaFragment extends Fragment {

    Disposable disposable;
    NetworkStatus status = new NetworkStatus();
    FragmentDetailIdeaBinding detailIdeaBinding;

    public static DetailIdeaFragment newInstance() {
        DetailIdeaFragment fragment = new DetailIdeaFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        detailIdeaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_idea, container, false);
        IdeaData editedIdea = DetailIdeaViewModel.choosenIdea.get();
        detailIdeaBinding.setObject(editedIdea);
        detailIdeaBinding.setStatus(status);
        detailIdeaBinding.setViewModel(new DetailIdeaViewModel());
        String currentStatus = detailIdeaBinding.getViewModel().getIdeaStatus().get();
        detailIdeaBinding.getViewModel().getCurrentIdea(editedIdea.getIdeaUser(), editedIdea.getIdeaTime()).observe(this, idea -> {
            detailIdeaBinding.setObject(idea);
        });
        detailIdeaBinding.setIdeaStatus(currentStatus);
        DetailIdeaViewModel.getStatuses().observe(this, statuses -> {
            detailIdeaBinding.setSpinAdapter(new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_dropdown_item, Objects.requireNonNull(statuses)));


        });

        return detailIdeaBinding.getRoot();
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
                        if (detailIdeaBinding != null) {
                            detailIdeaBinding.getRoot().findViewById(R.id.accept_idea_status_fab).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                        }

                    } else {
                        showSnackBar(detailIdeaBinding.getRoot());
                        status.isConnected.set(false);
                        if (detailIdeaBinding != null) {
                            detailIdeaBinding.getRoot().findViewById(R.id.accept_idea_status_fab).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorDisconnected)));

                        }
                    }

                });
    }

    private void showSnackBar(View view) {
        if (view != null) {
            Snackbar snackbar = Snackbar.make(view, R.string.connection_lost_message, Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }
    @Override
    public void onPause() {
        super.onPause();
        disposable.dispose();
    }
}
