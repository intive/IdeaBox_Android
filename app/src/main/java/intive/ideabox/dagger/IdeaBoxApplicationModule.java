package intive.ideabox.dagger;

import dagger.Module;
import dagger.Provides;
import intive.ideabox.model.authorization.FirebaseUserAuthenticate;
import intive.ideabox.model.authorization.UserAuthenticate;

@Module
public class IdeaBoxApplicationModule {

    @Provides
    public UserAuthenticate provideUserAuthenticate(){
        return new FirebaseUserAuthenticate();
    }
}
