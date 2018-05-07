package intive.ideabox.adapter;

import java.util.Collections;
import java.util.List;

import intive.ideabox.model.IdeaData;
import intive.ideabox.utility.RandomColorGenerator;

public class IdeaListAdapter extends SingleLayoutAdapter{

    private List<IdeaData> ideaData = Collections.emptyList();
    private RandomColorGenerator colorGenerator;

    public IdeaListAdapter(int layoutId) {
        super(layoutId);
        colorGenerator = new RandomColorGenerator();
    }

    @Override
    public int getItemCount() {
        return ideaData.size();
    }

    public void setData(List<IdeaData> data){
        ideaData = data;
        notifyDataSetChanged();
    }

    public int getColor(){
        return colorGenerator.getRandomColor();
    }

    @Override
    protected Object getObjForPosition(int position) {
        return ideaData.get(position);
    }
}
