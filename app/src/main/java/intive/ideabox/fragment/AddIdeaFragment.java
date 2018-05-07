package intive.ideabox.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import intive.ideabox.R;
import intive.ideabox.databinding.FragmentAddIdeaBinding;
import intive.ideabox.viewmodel.AddIdeaViewModel;

public class AddIdeaFragment extends android.support.v4.app.Fragment {

    public static AddIdeaFragment newInstance(){
        AddIdeaFragment fragment = new AddIdeaFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        FragmentAddIdeaBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_idea, container, false);
        String idea = "";
        AddIdeaViewModel viewModel = new AddIdeaViewModel();
        binding.setIdeaViewModel(viewModel);
        binding.setIdea(idea);
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

}