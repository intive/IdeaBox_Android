package intive.ideabox.models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DataProvider implements Data {

    private static DataProvider instance = null;

    protected DataProvider(){}

    public static DataProvider getInstance(){
        if (instance == null){
            instance = new DataProvider();
        }
        return instance;
    }

    @Override
    public Boolean saveIdea(String idea) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        IdeaData ideaData = new IdeaData(idea, "user");
        DatabaseReference myRef = database.getReference();
        myRef.child("ideas").child(ideaData.getIdeaUser()+ideaData.getIdeaTime()).setValue(ideaData);
        return true;

    }

    @Override
    public List<IdeaData> loadIdea() {

        final List<IdeaData> ideaDataList = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                    IdeaData post = dataSnapshot.getValue(IdeaData.class);
                    ideaDataList.add(new IdeaData(post.getIdeaText(),post.getIdeaUser(),post.getIdeaTime()));
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {}

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });
        return ideaDataList;
    }
}

