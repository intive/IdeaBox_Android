package intive.ideabox.model;

import java.util.Date;
public class IdeaData {
    private String ideaText;
    private String ideaUser;
    private long ideaTime;

    public IdeaData() {}

    public IdeaData(String ideaText, String ideaUser) {
        this.ideaText = ideaText;
        this.ideaUser = ideaUser;
        this.ideaTime = new Date().getTime();
    }

    public String getIdeaText() {
        return ideaText;
    }

    public void setIdeaText(String ideaText) {
        this.ideaText = ideaText;
    }

    public String getIdeaUser() {
        return ideaUser;
    }

    public void setIdeaUser(String ideaUser) {
        this.ideaUser = ideaUser;
    }

    public long getIdeaTime() {
        return ideaTime;
    }

    public void setIdeaTime(long ideaTime) {
        this.ideaTime = ideaTime;
    }
}