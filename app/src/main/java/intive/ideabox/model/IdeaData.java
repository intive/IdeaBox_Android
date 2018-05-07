package intive.ideabox.model;

import java.util.Date;

import intive.ideabox.utility.RandomColorGenerator;

public class IdeaData {
    private String ideaText;
    private String ideaUser;
    private long ideaTime;
    private int letterIconColor;

    public IdeaData() {
        letterIconColor = RandomColorGenerator.getInstance().getRandomColor();
    }

    public IdeaData(String ideaText, String ideaUser) {
        this.ideaText = ideaText;
        this.ideaUser = ideaUser;
        this.ideaTime = new Date().getTime();
        letterIconColor = RandomColorGenerator.getInstance().getRandomColor();
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

    public int getLetterIconColor() {
        return letterIconColor;
    }

    public void setLetterIconColor(int letterIconColor) {
        this.letterIconColor = letterIconColor;
    }
}