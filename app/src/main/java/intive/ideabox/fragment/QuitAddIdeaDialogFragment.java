package intive.ideabox.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import intive.ideabox.R;
import intive.ideabox.activity.MainActivity;
import intive.ideabox.databinding.FragmentQuitAddIdeaBinding;
import intive.ideabox.viewmodel.QuitAddIdeaViewModel;

public class QuitAddIdeaDialogFragment  extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        FragmentQuitAddIdeaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_quit_add_idea, null, false);
        QuitAddIdeaViewModel quitAddIdeaViewModel = new QuitAddIdeaViewModel(((MainActivity)getActivity()));
        binding.setDialogViewModel(quitAddIdeaViewModel);
        return new AlertDialog.Builder(getActivity(), R.style.ThemeOverlay_AppCompat_Dialog)
                .setView(binding.getRoot()).setMessage("Warning")
                .create();
    }
}
