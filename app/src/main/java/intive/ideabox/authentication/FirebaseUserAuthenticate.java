package intive.ideabox.authentication;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import intive.ideabox.R;
import intive.ideabox.utility.FragmentState;
import intive.ideabox.utility.NavigationUtils;

public class FirebaseUserAuthenticate implements UserAuthenticate {

    private static final String TAG = "$CLASS_NAME$";
    private FirebaseAuth firebaseAuth;
    public static boolean isUserAdmin= false;
    public FirebaseUserAuthenticate() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signIn(String email, String password) {

        if (firebaseAuth.getCurrentUser() == null) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithEmail:success");
                    NavigationUtils.getInstance().setSnackBar(R.string.snackbar_text_correct_login);
                    NavigationUtils.getInstance().setState(FragmentState.IdeaList);
                    isUserAdmin = true;
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    NavigationUtils.getInstance().setSnackBar(R.string.snackbar_text_incorrect_login);
                }
            });
        }
    }
}
