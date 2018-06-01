package intive.ideabox.dagger;

import dagger.Module;
import dagger.Provides;
import intive.ideabox.authentication.FirebaseUserAuthenticate;
import intive.ideabox.authentication.UserAuthenticate;

@Module
public class IdeaBoxApplicationModule {

    @Provides
    public UserAuthenticate provideUserAuthenticate(){
        return new FirebaseUserAuthenticate();
    }
}
