package intive.ideabox.model;

import io.reactivex.Observable;

public interface CloudProvider {

    Boolean saveIdea(String idea);

    void loadIdea(OnDataReadyListener callback);

    Observable<IdeaData> getIdeas();
}
