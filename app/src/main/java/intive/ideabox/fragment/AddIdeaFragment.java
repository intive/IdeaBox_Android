package intive.ideabox.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Formatter;
import java.util.Locale;

import intive.ideabox.R;
import intive.ideabox.databinding.FragmentAddIdeaBinding;
import intive.ideabox.viewmodel.AddIdeaViewModel;
import intive.ideabox.viewmodel.IdeaCountCharacters;

public class AddIdeaFragment extends android.support.v4.app.Fragment {
    private IdeaCountCharacters ideaCountCharacters;
    private String str;

    public static AddIdeaFragment newInstance(){
        AddIdeaFragment fragment = new AddIdeaFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        FragmentAddIdeaBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_idea, container, false);
        String idea = "";
        str = "00";
        ideaCountCharacters = new IdeaCountCharacters("0");
        AddIdeaViewModel viewModel = new AddIdeaViewModel();
        binding.setIdeaViewModel(viewModel);
        binding.setIdea(idea);
        binding.setIdeaCountCharacters(ideaCountCharacters);
binding.setStr(str);

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
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText edt = (EditText) getView().findViewById(R.id.editText);
        edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                str = String.valueOf(s.length());
                ideaCountCharacters.setNumberOfCharacters( String.valueOf(s.length()) );
            }
        });
    }
    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        View focusedView = getActivity().getCurrentFocus();

        if (focusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }

}