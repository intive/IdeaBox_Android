package intive.ideabox.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import intive.ideabox.utility.HashDataUtils;


public class FirebaseProvider implements CloudProvider {

    private static FirebaseProvider instance = null;
    private final String MAIN_DIRECTORY_NAME = "ideas";
    private final String VOTES_FIELD_NAME = "votes";
    private final String USER_FIELD_NAME = "user";
    private final String STATUS_DIRECTORY_NAME = "statuses";
    private MutableLiveData<List<IdeaData>> ideaMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<String>> ideaStatuses = new MutableLiveData<>();

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
        IdeaData ideaData = new IdeaData(idea, HashDataUtils.getHashedData(USER_FIELD_NAME));
        DatabaseReference myRef = getDBRef();
        myRef.child(MAIN_DIRECTORY_NAME).child(ideaData.getIdeaUser() + ideaData.getIdeaTime()).setValue(ideaData);
        Log.d("asd","awdadwa");
        return true;
    }

    @Override
    public Boolean editIdea(IdeaData choosenIdea) {
        Log.d("awadw",choosenIdea.getIdeaStatus());
        Log.d("awadw",choosenIdea.getIdeaUser());
        Log.d("awadw",String.valueOf(choosenIdea.getIdeaTime()));
        DatabaseReference myRef = getDBRef();
        Map<String,Object> taskMap = new HashMap<String,Object>();
        taskMap.put("ideaStatus", choosenIdea.getIdeaStatus());
        myRef.child(MAIN_DIRECTORY_NAME).child(choosenIdea.getIdeaUser().concat(String.valueOf(choosenIdea.getIdeaTime()))).updateChildren(taskMap);
        //child("ideaStatus").setValue(choosenIdea.getIdeaStatus());
        return true;

    }

    @Override
    public LiveData<List<IdeaData>> getIdeas() {
        final DatabaseReference myRef = getDBRef();
        myRef.child(MAIN_DIRECTORY_NAME).addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<IdeaData> ideaData = new ArrayList<>();
                dataSnapshot.getChildren().forEach(snapshot -> {
                    IdeaData data = snapshot.getValue(IdeaData.class);
                    HashMap<String, IdeaVote> ideaVotes = new HashMap<>();
                    snapshot.child(VOTES_FIELD_NAME).getChildren().forEach(voteSnapshot -> {
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
        DatabaseReference voteTemplate = myRef.child(MAIN_DIRECTORY_NAME).
                child(ideaData.getIdeaUser() + ideaData.getIdeaTime()).child(VOTES_FIELD_NAME).
                child(String.valueOf(ideaData.getIdeaTime()).concat(ideaData.getIdeaUser()));
        myRef.child(MAIN_DIRECTORY_NAME).child(ideaData.getIdeaUser() + ideaData.getIdeaTime()).child(VOTES_FIELD_NAME).
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

    public MutableLiveData<List<String>> getStatuses() {
        final DatabaseReference myRef = getDBRef();
        myRef.child(STATUS_DIRECTORY_NAME).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> tempIdeaStatuses = new ArrayList<String>();
                dataSnapshot.getChildren().forEach(snapshot -> {
                    String status = snapshot.child("status").getValue(String.class);
                    tempIdeaStatuses.add(status);
                });
                ideaStatuses.setValue(tempIdeaStatuses);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        return ideaStatuses;

    }


}