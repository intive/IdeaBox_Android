package intive.ideabox.model;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


public class FirebaseProvider implements CloudProvider {
    private static FirebaseProvider instance = null;
    private PublishSubject<IdeaData> publishSubject = PublishSubject.create();

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

    private DatabaseReference getDBRef() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        return database.getReference();
    }

    @Override
    public void loadIdea(@NonNull final OnDataReadyListener callback) {
        final DatabaseReference myRef = getDBRef();

        myRef.child("ideas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<IdeaData> ideaData = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    IdeaData data = snapshot.getValue(IdeaData.class);
                    ideaData.add(data);
                }

                callback.onDataReady(ideaData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
            }
        });
    }

    @Override
    public Observable<IdeaData> getIdeas() {
        final DatabaseReference myRef = getDBRef();

        myRef.child("ideas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    publishSubject.onNext(snapshot.getValue(IdeaData.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                publishSubject.onError(new Throwable(databaseError.getMessage()));
            }
        });

        return publishSubject;
    }
}


