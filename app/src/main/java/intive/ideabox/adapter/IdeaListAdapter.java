package intive.ideabox.adapter;

import java.util.List;

import intive.ideabox.model.IdeaData;

public class IdeaListAdapter extends SingleLayoutAdapter{

    private List<IdeaData> mIdeaData = null;

    public IdeaListAdapter(int layoutId) {
        super(layoutId);
    }

    @Override
    public int getItemCount() {
        if (mIdeaData == null)
            return 0;
        return mIdeaData.size();
    }

    public void setData(List<IdeaData> data){
        mIdeaData = data;
        notifyDataSetChanged();
    }

    @Override
    protected Object getObjForPosition(int position) {
        return mIdeaData.get(position);
    }
}
