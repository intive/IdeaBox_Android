package intive.ideabox.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IdeaData {
    private String ideaText;
    private String ideaUser;
    private long ideaTime;
    public HashMap<String,IdeaVote> getVotes() {
        return votes;
    }
    public void setVotes(HashMap<String,IdeaVote> votes) {
        this.votes = votes;
    }

    private HashMap<String,IdeaVote> votes;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean getSingleIdeaVote(String userName) {
        for (Map.Entry<String, IdeaVote> entry : votes.entrySet()) {
            if (entry.getKey().equals(userName)) {
                return true;
            }
        }
        return false;
    }
    public IdeaData() {}

    public IdeaData(String ideaText, String ideaUser) {
        this.ideaText = ideaText;
        this.ideaUser = ideaUser;
        this.ideaTime = new Date().getTime();
        this.votes = new HashMap<String,IdeaVote>();
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