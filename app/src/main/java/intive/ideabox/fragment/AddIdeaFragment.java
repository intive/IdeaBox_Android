package intive.ideabox.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import intive.ideabox.R;
import intive.ideabox.databinding.FragmentAddIdeaBinding;
import intive.ideabox.model.NetworkStatus;
import intive.ideabox.utility.RxBroadcastReceiver;
import intive.ideabox.viewmodel.AddIdeaViewModel;
import io.reactivex.disposables.Disposable;

public class AddIdeaFragment extends android.support.v4.app.Fragment {

    Disposable disposable;
    NetworkStatus status = new NetworkStatus();
    FragmentAddIdeaBinding binding;

    public static AddIdeaFragment newInstance() {
        AddIdeaFragment fragment = new AddIdeaFragment();
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
                        if(binding != null){
                            binding.getRoot().findViewById(R.id.fab).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                        }

                    } else {
                        showSnackBar(binding.getRoot());
                        status.isConnected.set(false);
                        if(binding != null){
                            binding.getRoot().findViewById(R.id.fab).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorDisconnected)));

                        }
                    }

                });


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_idea, container, false);
        String idea = "";
        AddIdeaViewModel viewModel = new AddIdeaViewModel();
        binding.setIdeaViewModel(viewModel);
        binding.setIdea(idea);
        binding.setStatus(status);
        binding.backgroundLayout.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    hideSoftKeyboard();
                }
                return false;
            }
        });
        return binding.getRoot();
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        View focusedView = getActivity().getCurrentFocus();

        if (focusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void showSnackBar(View view) {
        if (view != null) {
            Snackbar snackbar = Snackbar.make(view, R.string.connection_lost_message, Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

}