package hackwestern.hack.com.hackwestern.homescreen.interfaces;

import java.util.List;

import hackwestern.hack.com.hackwestern.homescreen.model.ChatFeedDataModel;
import hackwestern.hack.com.hackwestern.homescreen.parsers.ChatsResponseParser;

/**
 * Created by Sarthak on 18-11-2017
 */
public interface HomeScreenContract {
    interface View {
        void init();

        boolean isViewDestroyed();

        void showProgressBar(boolean show);

        void showTextNoChatRoom(boolean show);

        void showMyChats(List<ChatFeedDataModel> chatsModelList);
    }

    interface Presenter {
        void fetchUserConversations(String email);

        void onFetchUserChatsFailure();

        void onFetchUserChatsSuccess(List<ChatsResponseParser> chatsList);
    }
}
