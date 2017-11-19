package hackwestern.hack.com.hackwestern.getstarted.presenters;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import hackwestern.hack.com.hackwestern.R;
import hackwestern.hack.com.hackwestern.getstarted.interfaces.EmailSignUpContract;
import hackwestern.hack.com.hackwestern.getstarted.interfaces.GetStartedWebServiceInterface;
import hackwestern.hack.com.hackwestern.getstarted.parsers.SignupRequestDataParser;
import hackwestern.hack.com.hackwestern.getstarted.parsers.SignupResponseParser;
import hackwestern.hack.com.hackwestern.utils.Utils;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Sarthak on 18-11-2017
 */
public class EmailSignupPresenter implements EmailSignUpContract.Presenter {

    @Inject
    Context context;

    @Inject
    GetStartedWebServiceInterface webServiceInterface;

    private EmailSignUpContract.View view;

    public EmailSignupPresenter(EmailSignUpContract.View view) {
        this.view = view;
    }

    @Override
    public void callCreateProfileApi(SignupRequestDataParser requestDataParser) {
        if (Utils.isNetworkAvailable(context)) {
            view.showProgressBar(true);
            webServiceInterface.signUp(requestDataParser)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .onErrorResumeNext(new Func1<Throwable, Observable<? extends Response<SignupResponseParser>>>() {
                        @Override
                        public Observable<? extends Response<SignupResponseParser>> call(Throwable throwable) {
                            return null;
                        }
                    }).subscribe(new Subscriber<Response<SignupResponseParser>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    onCreateProfileApiFailure(e.getMessage());
                }

                @Override
                public void onNext(Response<SignupResponseParser> response) {
                    if (response.code() == 200)
                        onCreateProfileApiSuccess(response.body());
                    else
                        onCreateProfileApiFailure(response.body().getMessage());

                }
            });
        } else {
            Utils.showSnackBar(view.getParentView(), context.getString(R.string.string_error_no_network));
        }
    }

    @Override
    public void onCreateProfileApiSuccess(SignupResponseParser responseParser) {
        Utils.showSnackBar(view.getParentView(), responseParser.getMessage());
        view.showHomeScreen();
    }

    @Override
    public void onCreateProfileApiFailure(String message) {
        Log.i("ProfileApi", "Failure");
    }


}

