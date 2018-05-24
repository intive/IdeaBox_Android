package intive.ideabox.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import intive.ideabox.IdeaBoxApplication;
import intive.ideabox.authentication.UserAuthenticate;

public class AuthenticationViewModel extends ViewModel {

    @Inject
    UserAuthenticate userAuthenticate;

    private MutableLiveData<String> userEmail;
    private MutableLiveData<String> userPassword;

    public AuthenticationViewModel() {
        IdeaBoxApplication.applicationComponent.inject(this);
        userEmail = new MutableLiveData<>();
        userPassword = new MutableLiveData<>();
    }

    public void signIn() {
        if (userEmail.getValue() != null && userPassword.getValue() != null) {
            userAuthenticate.signIn(userEmail.getValue(), userPassword.getValue());
        }
    }

    public String getUserEmail() {
        return userEmail.getValue();
    }

    public void setUserEmail(String userEmail) {
        this.userEmail.setValue(userEmail);
    }

    public String getUserPassword() {
        return userPassword.getValue();
    }

    public void setUserPassword(String userPassword) {
        this.userPassword.setValue(userPassword);
    }
}
