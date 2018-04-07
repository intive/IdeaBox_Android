package intive.ideabox.models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DataProvider implements Data {

    @Override
    public Boolean saveIdea(String idea) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseDatabase.getInstance()
                    .getReference()
                    .push()
                    .setValue(new IdeaData(idea,
                            FirebaseAuth.getInstance()
                                    .getCurrentUser()
                                    .getDisplayName())
                    );
            return true;
        }
        return false;
    }

    @Override
    public List<IdeaData> loadIdea() {

        final List<IdeaData> ideaDataList = new ArrayList<>();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference();
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    IdeaData post = dataSnapshot.getValue(IdeaData.class);
                    ideaDataList.add(new IdeaData(post.getIdeaText(),post.getIdeaUser(),post.getIdeaTime()));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
        return ideaDataList;
    }
}

