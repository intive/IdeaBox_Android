package intive.ideabox.viewmodel;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import intive.ideabox.IdeaBoxApplication;
import intive.ideabox.authentication.UserAuthenticate;
import intive.ideabox.utility.FragmentState;
import intive.ideabox.utility.NavigationUtils;

public class AuthenticationViewModel extends ViewModel {

    @Inject
    UserAuthenticate userAuthenticate;

    public AuthenticationViewModel() {
        IdeaBoxApplication.applicationComponent.inject(this);
    }

    public void signIn(String email, String password) {
        userAuthenticate.signIn(email, password);
    }

    public void signOut() {
        userAuthenticate.signOut();
    }
}
