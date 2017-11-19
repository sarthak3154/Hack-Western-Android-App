package hackwestern.hack.com.hackwestern.homescreen.interfaces;

import hackwestern.hack.com.hackwestern.homescreen.parsers.RequestChatRequestParser;
import hackwestern.hack.com.hackwestern.homescreen.parsers.RequestChatResponseParser;

/**
 * Created by Sarthak on 19-11-2017
 */
public interface RequestChatScreenContract {
    interface View {
        void init();

        void showProgressBar(boolean show);

        void startChatScreen(String conversationId, String emailId, String name);
    }

    interface Presenter {
        void callRequestChatApi(RequestChatRequestParser requestParser);

        void onRequestChatApiSuccess(RequestChatResponseParser responseParser);

        void onRequesChatApiFailure(String message);
    }
}
