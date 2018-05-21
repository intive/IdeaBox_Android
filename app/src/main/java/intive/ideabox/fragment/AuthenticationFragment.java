package intive.ideabox.fragment;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
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

    FragmentAuthenticationBinding binding;

    public static AuthenticationFragment newInstance() {
        AuthenticationFragment authenticationFragment = new AuthenticationFragment();
        return authenticationFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_authentication, container, false);

        ViewModelProvider provider = ViewModelProviders.of(this);
        AuthenticationViewModel viewModel = provider.get(AuthenticationViewModel.class);

        binding.setViewModel(viewModel);

        return binding.getRoot();
    }
}
