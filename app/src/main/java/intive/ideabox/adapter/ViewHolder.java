package intive.ideabox.adapter;

import android.support.v7.widget.RecyclerView;

import intive.ideabox.databinding.RowIdeaListBinding;
import intive.ideabox.model.IdeaData;
import io.reactivex.annotations.NonNull;

public class ViewHolder extends RecyclerView.ViewHolder {
    private RowIdeaListBinding mBinding;

    public ViewHolder(RowIdeaListBinding binding){
        super(binding.getRoot());
        mBinding = binding;
    }

    public void bind(@NonNull IdeaData object){
        mBinding.setObject(object);
        mBinding.executePendingBindings();
    }
}
