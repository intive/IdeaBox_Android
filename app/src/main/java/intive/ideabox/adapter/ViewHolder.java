package intive.ideabox.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.android.databinding.library.baseAdapters.BR;

import io.reactivex.annotations.NonNull;

public class ViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    public ViewHolder(ViewDataBinding binding){
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(@NonNull Object object){
        binding.setVariable(BR.object, object);
        binding.executePendingBindings();
    }
    public ViewDataBinding getBinding(){
        return binding;
    }
}
