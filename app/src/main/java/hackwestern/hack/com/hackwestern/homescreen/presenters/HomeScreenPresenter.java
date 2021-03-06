package hackwestern.hack.com.hackwestern.homescreen.presenters;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.inject.Inject;

import hackwestern.hack.com.hackwestern.homescreen.interfaces.HomeScreenContract;
import hackwestern.hack.com.hackwestern.homescreen.interfaces.HomeScreenWebServiceInterface;
import hackwestern.hack.com.hackwestern.homescreen.model.ChatFeedDataModel;
import hackwestern.hack.com.hackwestern.homescreen.parsers.ChatsResponseParser;
import hackwestern.hack.com.hackwestern.utils.Utils;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Sarthak on 18-11-2017
 */
public class HomeScreenPresenter implements HomeScreenContract.Presenter {
    @Inject
    Context context;

    @Inject
    HomeScreenWebServiceInterface webServiceInterface;

    private HomeScreenContract.View view;

    public HomeScreenPresenter(HomeScreenContract.View view) {
        this.view = view;
    }

    @Override
    public void fetchUserConversations(String email) {
        if (Utils.isNetworkAvailable(context)) {
            webServiceInterface.getUserAllChats(email).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<Response<List<ChatsResponseParser>>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            onFetchUserChatsFailure();
                        }

                        @Override
                        public void onNext(Response<List<ChatsResponseParser>> response) {
                            onFetchUserChatsSuccess(response.body());
                        }
                    });
        }
    }

    @Override
    public void onFetchUserChatsFailure() {

    }

    @Override
    public void onFetchUserChatsSuccess(List<ChatsResponseParser> chatsResponseParserList) {
        if (chatsResponseParserList.size() > 0) {
            List<ChatFeedDataModel> modelList = new ArrayList<>();
            modelList.clear();
            for (ChatsResponseParser responseParser : chatsResponseParserList) {
                ChatFeedDataModel dataModel = new ChatFeedDataModel();
                dataModel.setEmail(responseParser.getEmail());
                dataModel.setMessageId(responseParser.getMessageId());
                dataModel.setName(responseParser.getName());
                modelList.add(dataModel);
            }
            view.showMyChats(modelList);
        } else {
            view.showTextNoChatRoom(true);
        }
    }
}
