package hackwestern.hack.com.hackwestern.homescreen.interfaces;

/**
 * Created by Sarthak on 18-11-2017
 */
public interface HomeScreenContract {
    interface View {
        void init();
    }

    interface Presenter {
        void fetchUserConversations(String email);

        void onFetchUserChatsFailure();

        void onFetchUserChatsSuccess(boolean isEmpty);
    }
}
