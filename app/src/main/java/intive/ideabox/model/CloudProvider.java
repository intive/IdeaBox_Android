package intive.ideabox.model;

import android.arch.lifecycle.LiveData;
import android.support.v4.app.FragmentActivity;

import java.util.List;

public interface CloudProvider {

    Boolean saveIdea(String idea, FragmentActivity fragmentActivity);

    LiveData<List<IdeaData>> getIdeas();

    void editIdea(IdeaData editedIdea);
}
