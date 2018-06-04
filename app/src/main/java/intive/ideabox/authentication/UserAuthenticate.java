package intive.ideabox.authentication;

public interface UserAuthenticate {

    void signIn(String email, String password);

    boolean getUserStatus();
}
