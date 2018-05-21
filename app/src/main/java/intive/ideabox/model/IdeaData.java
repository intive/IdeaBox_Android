package intive.ideabox.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import intive.ideabox.utility.RandomColorGenerator;

public class IdeaData {
    private String ideaText;
    private String ideaUser;
    private long ideaTime;
    private HashMap<String, IdeaVote> votes;

    private int letterIconColor;

    public IdeaData() {}

    public IdeaData(String ideaText, String ideaUser) {
        this.ideaText = ideaText;
        this.ideaUser = ideaUser;
        this.ideaTime = new Date().getTime();
        this.votes = new HashMap<String, IdeaVote>();
        letterIconColor = RandomColorGenerator.getInstance().getRandomColor();
    }

    public HashMap<String, IdeaVote> getVotes() {
        return votes;
    }

    public void setVotes(HashMap<String, IdeaVote> votes) {
        this.votes = votes;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean getSingleIdeaVote(String userName) {
        for (Map.Entry<String, IdeaVote> entry : votes.entrySet()) {
            if (entry.getKey().equals(userName)) {
                return true;
            }
        }
        return false;
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