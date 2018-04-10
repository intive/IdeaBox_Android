package intive.ideabox.models;

import java.util.Date;

public class IdeaData {
    public String ideatext;
    public String ideauser;
    public long ideatime;

    public IdeaData(String ideaText, String ideaUser) {
        this.ideatext = ideaText;
        this.ideauser = ideaUser;
        this.ideatime = new Date().getTime();
    }
    public IdeaData(String ideaText, String ideaUser, long ideaTime ) {
        this.ideatext = ideaText;
        this.ideauser = ideaUser;
        this.ideatime = ideaTime;
    }
    public IdeaData() {}
    public String getIdeatext() {
        return ideatext;
    }

    public void setIdeatext(String ideatext) {
        this.ideatext = ideatext;
    }

    public String getIdeauser() {
        return ideauser;
    }

    public void setIdeauser(String ideauser) {
        this.ideauser = ideauser;
    }

    public long getIdeatime() {
        return ideatime;
    }

    public void setIdeatime(long ideatime) {
        this.ideatime = ideatime;
    }
}
