package intive.ideabox.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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

    private DatabaseReference getDBRef() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        return database.getReference();
    }

    @Override
    public Boolean saveIdea(String idea) {
        IdeaData ideaData = new IdeaData(idea, "user");
        DatabaseReference myRef = getDBRef();
        myRef.child("ideas").child(ideaData.getIdeaUser() + ideaData.getIdeaTime()).setValue(ideaData);
        return true;

    }

    @Override
    public LiveData<List<IdeaData>> getIdeas() {
        final DatabaseReference myRef = getDBRef();

        myRef.child("ideas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<IdeaData> ideaData = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    IdeaData data = snapshot.getValue(IdeaData.class);
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
}


