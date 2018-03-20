package ideabox.intive.mvvm;

/**
 * Created by Artur on 16.03.2018.
 * Basic Model for application
 */

public class IdeaModel {
    public Boolean IdeaItem;

    public IdeaModel(boolean ideaItem) {
        this.IdeaItem = ideaItem;
    }

    public Boolean getIdeaItem() {
        return IdeaItem;
    }

    public void setIdeaItem(Boolean ideaItem) {
        IdeaItem = ideaItem;
    }
}