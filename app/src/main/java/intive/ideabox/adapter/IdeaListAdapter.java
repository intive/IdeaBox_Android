package intive.ideabox.adapter;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import intive.ideabox.R;
import intive.ideabox.databinding.RowIdeaListBinding;
import intive.ideabox.model.IdeaData;

public class IdeaListAdapter extends RecyclerView.Adapter<ViewHolder>  {
    private List<IdeaData> mData = new ArrayList<>();

    private Cursor mCursor;

    public void setCursor(Cursor cursor){
        if(cursor != null){
            mCursor = cursor;
        }
        notifyDataSetChanged();
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
