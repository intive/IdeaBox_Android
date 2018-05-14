package intive.ideabox.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.firebase.database.DatabaseReference;

import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class FirebaseProvider implements CloudProvider {
    @Mock
    private DatabaseReference mockFirebaseDatabase;
    private List<IdeaData> mockedData = Collections.emptyList();
    private long fetchDelay = 500;

    private static FirebaseProvider instance = null;
    private MutableLiveData<List<IdeaData>> ideaMutableLiveData = new MutableLiveData<>();

    protected FirebaseProvider() {
        initMocks(this);

        when(mockFirebaseDatabase.child(any())).thenReturn(mockFirebaseDatabase);
    }

    public static FirebaseProvider getInstance() {
        if (instance == null) {
            instance = new FirebaseProvider();
        }
        return instance;
    }

    private DatabaseReference getDBRef() {
        return mockFirebaseDatabase;
    }

    public void setMockedData(List<IdeaData> mockedData) {
        this.mockedData = mockedData;
    }

    public void setFetchDelay(long fetchDelay) {
        this.fetchDelay = fetchDelay;
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
        Observable.just(mockedData)
                .delay(fetchDelay, TimeUnit.MILLISECONDS)
                .subscribe(data -> ideaMutableLiveData.postValue(data));


        return ideaMutableLiveData;
    }
}


