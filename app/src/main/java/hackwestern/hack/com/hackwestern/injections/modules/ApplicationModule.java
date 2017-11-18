package hackwestern.hack.com.hackwestern.injections.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hackwestern.hack.com.hackwestern.BaseApplication;

/**
 * Created by Sarthak on 18-11-2017
 */
@Module
public class ApplicationModule {
    private BaseApplication application;

    public ApplicationModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return application;
    }
}