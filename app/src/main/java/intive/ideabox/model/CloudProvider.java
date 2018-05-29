package intive.ideabox.model;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface CloudProvider {

    Boolean saveIdea(String idea);

    LiveData<List<IdeaData>> getIdeas();

    Boolean editIdea(IdeaData editedIdea);
}
