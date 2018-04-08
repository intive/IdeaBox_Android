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
        myRef.child("ideas").child(ideaData.ideauser+ideaData.ideatime).setValue(ideaData);
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
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    IdeaData data = snapshot.getValue(IdeaData.class);
                    ideaData.add(data);
                }
                //To do: implement the way to notify adapter about changes
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                }
        });

        return ideaData;
    }
}


