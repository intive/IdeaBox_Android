package intive.ideabox.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import intive.ideabox.R;
import intive.ideabox.model.AdapterListener;

public abstract class BaseAdapter extends RecyclerView.Adapter<ViewHolder> {

    private AdapterListener mListener;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object object = getObjForPosition(position);
        holder.bind(object);
        holder.getBinding().getRoot().findViewById(R.id.buttonVote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(view);
            }
        });
    }
    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    protected abstract Object getObjForPosition(int position);

    public void setAdapterListener(AdapterListener listener) {
        mListener = listener;
    }
    protected abstract int getLayoutIdForPosition(int position);
}
