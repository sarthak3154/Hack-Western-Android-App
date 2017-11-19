package hackwestern.hack.com.hackwestern.getstarted.interfaces;

import android.support.design.widget.CoordinatorLayout;

import hackwestern.hack.com.hackwestern.getstarted.parsers.SignupRequestDataParser;
import hackwestern.hack.com.hackwestern.getstarted.parsers.SignupResponseParser;

/**
 * Created by Sarthak on 18-11-2017
 */
public interface EmailSignUpContract {
    interface View {
        void init();

        CoordinatorLayout getParentView();

        void setSignUpDetailsEnabled(boolean show);

        void showProgressBar(boolean show);
    }

    interface Presenter {

        void callCreateProfileApi(SignupRequestDataParser requestDataParser);

        void onCreateProfileApiSuccess(SignupResponseParser responseParser);

        void onCreateProfileApiFailure(String message);
    }
}
