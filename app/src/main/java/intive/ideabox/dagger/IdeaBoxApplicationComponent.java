package intive.ideabox.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {IdeaBoxApplicationModule.class})
public interface IdeaBoxApplicationComponent {

}
