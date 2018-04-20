package intive.ideabox.adapter;

import java.util.List;

import intive.ideabox.model.IdeaData;

public class IdeaListAdapter extends SingleLayoutAdapter{

    private List<IdeaData> ideaData = null;

    public IdeaListAdapter(int layoutId) {
        super(layoutId);
    }

    @Override
    public int getItemCount() {
        if (ideaData == null)
            return 0;
        return ideaData.size();
    }

    public void setData(List<IdeaData> data){
        ideaData = data;
        notifyDataSetChanged();
    }

    @Override
    protected Object getObjForPosition(int position) {
        return ideaData.get(position);
    }
}
