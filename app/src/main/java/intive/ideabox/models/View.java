package intive.ideabox.models;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import intive.ideabox.R;


public class View extends Fragment  {

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view, container, false);
    }

}
