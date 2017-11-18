package hackwestern.hack.com.hackwestern.injections.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.*;
import hackwestern.hack.com.hackwestern.BaseApplication;
import hackwestern.hack.com.hackwestern.injections.modules.ApiModule;
import hackwestern.hack.com.hackwestern.injections.modules.ApplicationModule;

/**
 * Created by Sarthak on 18-11-2017
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    Context getApplicationContext();

    ApiComponent plusApiComponent(ApiModule apiModule);

    void inject(BaseApplication application);
}
