package intive.ideabox.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import intive.ideabox.R;
import intive.ideabox.databinding.FragmentAuthenticationBinding;
import intive.ideabox.viewmodel.AuthenticationViewModel;

public class AuthenticationFragment extends Fragment {

    AuthenticationViewModel viewModel;

    public static AuthenticationFragment newInstance() {
        AuthenticationFragment authenticationFragment = new AuthenticationFragment();
        return authenticationFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAuthenticationBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_authentication, container, false);
        viewModel = new AuthenticationViewModel();
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }
}
