package intive.ideabox.model;

import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;


public class AbstractEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @PrimaryKey(autoGenerate = true)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
