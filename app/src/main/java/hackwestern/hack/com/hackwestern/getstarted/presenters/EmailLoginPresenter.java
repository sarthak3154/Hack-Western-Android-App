package hackwestern.hack.com.hackwestern.getstarted.presenters;

import android.content.Context;

import javax.inject.Inject;

import hackwestern.hack.com.hackwestern.R;
import hackwestern.hack.com.hackwestern.getstarted.interfaces.EmailLoginContract;
import hackwestern.hack.com.hackwestern.getstarted.interfaces.GetStartedWebServiceInterface;
import hackwestern.hack.com.hackwestern.getstarted.parsers.LoginRequestDataParser;
import hackwestern.hack.com.hackwestern.getstarted.parsers.LoginResponseParser;
import hackwestern.hack.com.hackwestern.utils.AppConstants;
import hackwestern.hack.com.hackwestern.utils.Utils;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Lenovo on 18-11-2017
 */
public class EmailLoginPresenter implements EmailLoginContract.Presenter {

    @Inject
    Context context;

    @Inject
    GetStartedWebServiceInterface webServiceInterface;

    private EmailLoginContract.View view;

    public EmailLoginPresenter(EmailLoginContract.View view) {
        this.view = view;
    }

    public void callLogInApi(LoginRequestDataParser dataParser) {
        if (Utils.isNetworkAvailable(context)) {
            view.showProgressBar(true);
            webServiceInterface.signIn(dataParser).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Response<LoginResponseParser>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            onEmailLoginFailure(e.getMessage());
                        }

                        @Override
                        public void onNext(Response<LoginResponseParser> response) {
                            if (response.isSuccess()) {
                                LoginResponseParser parser = response.body();

                            }
                        }
                    });

        } else {
            Utils.showSnackBar(view.getParentView(), context.getString(R.string.string_error_no_network));
        }
    }

    @Override
    public void onEmailLoginFailure(String message) {

    }

    @Override
    public void onEmailLoginSuccess(LoginResponseParser responseParser) {

    }


}
