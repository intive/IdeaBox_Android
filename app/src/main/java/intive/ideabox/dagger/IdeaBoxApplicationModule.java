package intive.ideabox.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import intive.ideabox.model.CloudProvider;
import intive.ideabox.model.FirebaseProvider;

@Module
public class IdeaBoxApplicationModule {

	@Provides
	@Singleton
	public CloudProvider provideCloudProvider() {
		return new FirebaseProvider();
	}
}
