package hackwestern.hack.com.hackwestern.injections.components;

import javax.inject.Singleton;

import dagger.Subcomponent;
import hackwestern.hack.com.hackwestern.homescreen.presenters.ChatScreenPresenter;
import hackwestern.hack.com.hackwestern.homescreen.presenters.HomeScreenPresenter;
import hackwestern.hack.com.hackwestern.injections.modules.ApiModule;

/**
 * Created by Sarthak on 18-11-2017
 */
@Singleton
@Subcomponent(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(ChatScreenPresenter presenter);

    void inject(HomeScreenPresenter presenter);
}
