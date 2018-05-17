package intive.ideabox.model.authorization;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseUserAuthenticate implements UserAuthenticate {

    private static final String TAG = "$CLASS_NAME$";

    @Override
    public void signIn(String email, String password) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithEmail:success");
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                }
            });
        }
    }
}
