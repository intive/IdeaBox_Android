package intive.ideabox;

import android.support.multidex.MultiDexApplication;

import intive.ideabox.dagger.DaggerIdeaBoxApplicationComponent;
import intive.ideabox.dagger.IdeaBoxApplicationComponent;
import intive.ideabox.dagger.IdeaBoxApplicationModule;

public class IdeaBoxApplication extends MultiDexApplication {
    public static IdeaBoxApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerIdeaBoxApplicationComponent
                .builder()
                .ideaBoxApplicationModule(new IdeaBoxApplicationModule())
                .build();
    }
}
