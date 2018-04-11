package intive.ideabox.model;

import java.util.List;

public interface CloudProvider {

    Boolean saveIdea(String idea);
    List<IdeaData> loadIdea();

}
