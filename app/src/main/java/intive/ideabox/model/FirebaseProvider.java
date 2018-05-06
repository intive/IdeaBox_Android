package intive.ideabox.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.common.hash.Hashing;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FirebaseProvider implements CloudProvider {

    private static FirebaseProvider instance = null;
    private MutableLiveData<List<IdeaData>> ideaMutableLiveData = new MutableLiveData<>();

    protected FirebaseProvider() {
    }

    public static FirebaseProvider getInstance() {
        if (instance == null) {
            instance = new FirebaseProvider();
        }
        return instance;
    }

    public DatabaseReference getDBRef() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        return database.getReference();
    }

    @Override
    public Boolean saveIdea(String idea) {
        IdeaData ideaData = new IdeaData(idea, Hashing.sha256()
                .hashString("user", StandardCharsets.UTF_8)
                .toString());
        DatabaseReference myRef = getDBRef();
        myRef.child("ideas").child(ideaData.getIdeaUser() + ideaData.getIdeaTime()).setValue(ideaData);
        return true;

    }

    @Override
    public LiveData<List<IdeaData>> getIdeas() {
        final DatabaseReference myRef = getDBRef();
        myRef.child("ideas").addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<IdeaData> ideaData = new ArrayList<>();
                dataSnapshot.getChildren().forEach(snapshot->{
                    IdeaData data = snapshot.getValue(IdeaData.class);
                    HashMap<String, IdeaVote> ideaVotes = new HashMap<>();
                    snapshot.child("votes").getChildren().forEach(voteSnapshot->{
                        IdeaVote vote = voteSnapshot.getValue(IdeaVote.class);
                        ideaVotes.put(vote.getIdeaUser(), vote);
                    });
                    data.setVotes(ideaVotes);
                    ideaData.add(data);
                });
                ideaMutableLiveData.postValue(ideaData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return ideaMutableLiveData;
    }

    public void addVoteForIdea(IdeaData ideaData) {
        DatabaseReference myRef = getDBRef();
        DatabaseReference voteTemplate = myRef.child("ideas").child(ideaData.getIdeaUser() + ideaData.getIdeaTime()).child("votes").
                child(String.valueOf(ideaData.getIdeaTime()).concat(ideaData.getIdeaUser()));
        myRef.child("ideas").child(ideaData.getIdeaUser() + ideaData.getIdeaTime()).child("votes").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild(String.valueOf(ideaData.getIdeaTime()).concat(ideaData.getIdeaUser()))) {
                            voteTemplate.setValue(null);

                        } else {
                            voteTemplate.setValue(new IdeaVote(ideaData.getIdeaTime(), ideaData.getIdeaUser()));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}