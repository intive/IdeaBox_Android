package intive.ideabox.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import intive.ideabox.R;
import intive.ideabox.databinding.RowIdeaListBinding;
import intive.ideabox.model.IdeaData;
import io.reactivex.annotations.NonNull;

public class IdeaListAdapter extends RecyclerView.Adapter<ViewHolder>  {
    private List<IdeaData> mData;

    public IdeaListAdapter(@NonNull List<IdeaData> data){
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowIdeaListBinding binding = DataBindingUtil.inflate(inflater, R.layout.row_idea_list, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IdeaData ideaData = mData.get(position);
        holder.bind(ideaData);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
