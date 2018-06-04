package intive.ideabox.adapter;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

public class StatusSpinnerBindingAdapter {
    @BindingAdapter(value = {"ideaStatus",
            "ideaStatusAttrChanged"}, requireAll = false)
    public static void setIdeaStatus(final AppCompatSpinner spinner,
                                     final String selectedIdeaStatus,
                                     final InverseBindingListener changeListener) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (changeListener != null) {
                    changeListener.onChange();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                changeListener.onChange();
            }
        });

        spinner.setSelection(getIndexOfItem(spinner, selectedIdeaStatus));
    }

    @InverseBindingAdapter(attribute = "ideaStatus",
            event = "ideaStatusAttrChanged")
    public static String getIdeaStatus(final AppCompatSpinner spinner) {
        return (String) spinner.getSelectedItem();
    }

    private static int getIndexOfItem(AppCompatSpinner spinner, String item) {
        Adapter a = spinner.getAdapter();
        for (int i = 0; i < a.getCount(); i++) {
            if ((a.getItem(i)).toString().equals(item)) {
                return i;
            }
        }

        return 0;
    }
}
