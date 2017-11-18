package hackwestern.hack.com.hackwestern;

import android.content.Context;

import hackwestern.hack.com.hackwestern.injections.components.ApplicationComponent;
import hackwestern.hack.com.hackwestern.injections.modules.ApplicationModule;
import hackwestern.hack.com.hackwestern.injections.components.DaggerApplicationComponent;

/**
 * Created by Sarthak on 18-11-2017
 */
public class BaseApplication extends com.activeandroid.app.Application {

    private int resumeCount;
    private int pauseCount;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    private void initializeApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void updateResumeCount() {
        this.resumeCount++;
    }

    public void updatePauseCount() {
        this.pauseCount++;
    }

    public int getResumeCount() {
        return resumeCount;
    }

    public int getPauseCount() {
        return pauseCount;
    }


}
