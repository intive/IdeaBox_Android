package intive.ideabox.model;

import android.annotation.SuppressLint;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.android.databinding.library.baseAdapters.BR;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import intive.ideabox.utility.UserDataUtils;
import intive.ideabox.utility.RandomColorGenerator;

public class IdeaData extends BaseObservable {
    public int votesNumber;
    private String ideaText;
    private String ideaUser;
    private long ideaTime;
    private String ideaStatus;
    private HashMap<String, IdeaVote> votes;
    private int letterIconColor;

    public IdeaData() {
    }

    public IdeaData(String ideaText, String ideaUser) {
        this.ideaText = ideaText;
        this.ideaUser = ideaUser;
        this.ideaTime = new Date().getTime();
        this.ideaStatus = "To Do";
        this.votes = new HashMap<>();
        letterIconColor = RandomColorGenerator.getInstance().getRandomColor();
        this.votesNumber = 0;

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
            if (entry.getKey().equals(UserDataUtils.getImeiUser())) {
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

    @Bindable
    public String getIdeaStatus() {
        return ideaStatus;
    }

    public void setIdeaStatus(String ideaStatus) {
        this.ideaStatus = ideaStatus;
        notifyPropertyChanged(BR.ideaStatus);
    }

    public int getVotesNumber() {
        if (votes != null) {
            return votes.size();
        } else {
            return 0;
        }
    }

    public void setVotesNumber(int voteNumber) {
        this.votesNumber = voteNumber;
    }

    public String ideaTimeToDate() {
        Date date = new Date(ideaTime);
        @SuppressLint("SimpleDateFormat") Format format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }
}