package intive.ideabox.model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FirebaseProvider implements CloudProvider {

    private static FirebaseProvider instance = null;

    protected FirebaseProvider() {
    }

    public static FirebaseProvider getInstance() {
        if (instance == null) {
            instance = new FirebaseProvider();
        }
        return instance;
    }

    @Override
    public Boolean saveIdea(String idea) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        IdeaData ideaData = new IdeaData(idea, "user");
        DatabaseReference myRef = database.getReference();
        myRef.child("ideas").child(ideaData.getIdeaUser() + ideaData.getIdeaTime()).setValue(ideaData);
        return true;

    }

    @Override
    public List<IdeaData> loadIdea() {
        final List<IdeaData> ideaData = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        myRef.child("ideas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    IdeaData data = snapshot.getValue(IdeaData.class);
                    ideaData.add(data);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return ideaData;
    }
}


