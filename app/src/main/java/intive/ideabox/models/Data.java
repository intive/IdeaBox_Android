package intive.ideabox.models;

import java.util.List;

public interface Data {

    Boolean saveIdea(String idea);
    List<IdeaData> loadIdea();

}
