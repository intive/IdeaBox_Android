package intive.ideabox.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import intive.ideabox.utility.UserDataUtils;


public class FirebaseProvider implements CloudProvider {

    private static FirebaseProvider instance = null;
    private final String MAIN_DIRECTORY_NAME = "ideas";
    private final String VOTES_FIELD_NAME = "votes";
    private final String USER_FIELD_NAME = "user";
    private final String IDEA_STATUS_FIELD_NAME = "ideaStatus";
    private final String STATUS_DIRECTORY_NAME = "statuses";
    private final String STATUS_FIELD_NAME = "status";
    private MutableLiveData<List<IdeaData>> ideaMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<String>> ideaStatuses = new MutableLiveData<>();
    private MutableLiveData<IdeaData> currentIdea = new MutableLiveData<>();

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
        IdeaData ideaData = new IdeaData(idea, UserDataUtils.getImeiUser());
        DatabaseReference myRef = getDBRef();
        myRef.child(MAIN_DIRECTORY_NAME).child(ideaData.getIdeaUser() + ideaData.getIdeaTime()).setValue(ideaData);
        return true;
    }

    @Override
    public void editIdea(IdeaData choosenIdea) {
        DatabaseReference myRef = getDBRef();
        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put(IDEA_STATUS_FIELD_NAME, choosenIdea.getIdeaStatus());
        myRef.child(MAIN_DIRECTORY_NAME).child(choosenIdea.getIdeaUser().concat(String.valueOf(choosenIdea.getIdeaTime()))).updateChildren(taskMap);

    }

    @Override
    public LiveData<List<IdeaData>> getIdeas() {
        final DatabaseReference myRef = getDBRef();
        myRef.child(MAIN_DIRECTORY_NAME).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<IdeaData> ideaData = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    IdeaData data = snapshot.getValue(IdeaData.class);
                    HashMap<String, IdeaVote> ideaVotes = new HashMap<>();
                    for (DataSnapshot voteSnapshot : snapshot.child(VOTES_FIELD_NAME).getChildren()) {
                        IdeaVote vote = voteSnapshot.getValue(IdeaVote.class);
                        ideaVotes.put(vote.getIdeaUser(), vote);
                    }
                    data.setVotes(ideaVotes);
                    ideaData.add(data);
                }
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
                child(String.valueOf(ideaData.getIdeaTime()).concat(UserDataUtils.getImeiUser()));
        myRef.child(MAIN_DIRECTORY_NAME).child(ideaData.getIdeaUser() + ideaData.getIdeaTime()).child(VOTES_FIELD_NAME).
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild(String.valueOf(ideaData.getIdeaTime()).concat(UserDataUtils.getImeiUser()))) {
                            voteTemplate.setValue(null);

                        } else {
                            voteTemplate.setValue(new IdeaVote(ideaData.getIdeaTime(), UserDataUtils.getImeiUser()));
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
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> tempIdeaStatuses = new ArrayList<String>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String status = snapshot.child(STATUS_FIELD_NAME).getValue(String.class);
                    tempIdeaStatuses.add(status);
                }

                ideaStatuses.setValue(tempIdeaStatuses);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        return ideaStatuses;

    }

    public MutableLiveData<IdeaData> getCurrentIdea(String ideaUser, long ideaTime) {
        final DatabaseReference myRef = getDBRef();
        myRef.child(MAIN_DIRECTORY_NAME).
                child(ideaUser + ideaTime).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentIdea.setValue(dataSnapshot.getValue(IdeaData.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        return currentIdea;
    }
}