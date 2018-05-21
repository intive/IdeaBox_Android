package intive.ideabox.authentication;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import intive.ideabox.utility.FragmentState;
import intive.ideabox.utility.NavigationUtils;

public class FirebaseUserAuthenticate implements UserAuthenticate {

    private static final String TAG = "$CLASS_NAME$";
    private FirebaseAuth firebaseAuth;

    public FirebaseUserAuthenticate() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signIn(String email, String password) {

        if (firebaseAuth.getCurrentUser() == null) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithEmail:success");
                    NavigationUtils.getInstance().setState(FragmentState.IdeaList);
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                }
            });
        }
    }

    @Override
    public void signOut() {
        firebaseAuth.signOut();
        Log.d(TAG, "signOut");
    }
}
