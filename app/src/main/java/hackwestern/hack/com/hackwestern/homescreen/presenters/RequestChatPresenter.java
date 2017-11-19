package hackwestern.hack.com.hackwestern.homescreen.presenters;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import hackwestern.hack.com.hackwestern.homescreen.interfaces.HomeScreenWebServiceInterface;
import hackwestern.hack.com.hackwestern.homescreen.interfaces.RequestChatScreenContract;
import hackwestern.hack.com.hackwestern.homescreen.parsers.RequestChatRequestParser;
import hackwestern.hack.com.hackwestern.homescreen.parsers.RequestChatResponseParser;
import hackwestern.hack.com.hackwestern.utils.Utils;
import retrofit2.Response;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Sarthak on 19-11-2017
 */
public class RequestChatPresenter implements RequestChatScreenContract.Presenter {

    @Inject
    Context context;

    @Inject
    HomeScreenWebServiceInterface webServiceInterface;

    private RequestChatScreenContract.View view;

    public RequestChatPresenter(RequestChatScreenContract.View view) {
        this.view = view;
    }


    @Override
    public void callRequestChatApi(final RequestChatRequestParser requestParser) {
        if (Utils.isNetworkAvailable(context)) {
            view.showProgressBar(true);
            webServiceInterface.requestInitializeChat(requestParser)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<Response<RequestChatResponseParser>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            onRequesChatApiFailure(e.getMessage());
                        }

                        @Override
                        public void onNext(Response<RequestChatResponseParser> response) {
                            if (response.isSuccess() || response.code() == 200)
                                onRequestChatApiSuccess(response.body());
                            else
                                onRequesChatApiFailure(response.message());
                        }
                    });
        }
    }

    @Override
    public void onRequestChatApiSuccess(RequestChatResponseParser responseParser) {
        view.showProgressBar(false);
        view.startChatScreen(responseParser.getMessageId(), responseParser.getUser2Email(),
                responseParser.getUser2Email());

    }

    @Override
    public void onRequesChatApiFailure(String message) {
        view.showProgressBar(false);
    }
}
