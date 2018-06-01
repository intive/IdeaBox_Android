package intive.ideabox.dagger;

import javax.inject.Singleton;

import dagger.Component;
import intive.ideabox.viewmodel.AuthenticationViewModel;

@Singleton
@Component(modules = {IdeaBoxApplicationModule.class})
public interface IdeaBoxApplicationComponent {

    void inject(AuthenticationViewModel authenticationViewModel);
}
