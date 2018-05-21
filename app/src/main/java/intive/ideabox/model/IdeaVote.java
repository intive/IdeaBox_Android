package intive.ideabox.model;

public class IdeaVote {
    private long ideaTime;
    private String ideaUser;

    public IdeaVote() {

    }

    public IdeaVote(long ideaTime, String ideaUser) {
        this.ideaTime = ideaTime;
        this.ideaUser = ideaUser;
    }

    public long getIdeaTime() {
        return ideaTime;
    }

    public void setIdeaTime(long ideaTime) {
        this.ideaTime = ideaTime;
    }

    public String getIdeaUser() {
        return ideaUser;
    }

    public void setIdeaUser(String ideaUser) {
        this.ideaUser = ideaUser;
    }
}


