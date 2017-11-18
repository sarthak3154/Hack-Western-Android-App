package hackwestern.hack.com.hackwestern.homescreen.presenters;

import android.content.Context;

import javax.inject.Inject;

import hackwestern.hack.com.hackwestern.homescreen.interfaces.ChatScreenContract;

/**
 * Created by Lenovo on 18-11-2017.
 */

public class ChatScreenPresenter implements ChatScreenContract.Presenter {
    @Inject
    Context context;

    ChatScreenContract.View view;

    public ChatScreenPresenter(ChatScreenContract.View view) {
        this.view = view;
    }


}
