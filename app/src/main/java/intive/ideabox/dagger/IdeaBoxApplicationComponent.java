package intive.ideabox.dagger;

import javax.inject.Singleton;

import dagger.Component;
import intive.ideabox.viewmodel.AddIdeaViewModel;

@Singleton
@Component(modules = {IdeaBoxApplicationModule.class})
public interface IdeaBoxApplicationComponent {
	void inject(AddIdeaViewModel vm);
}
