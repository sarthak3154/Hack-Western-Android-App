package hackwestern.hack.com.hackwestern.getstarted.interfaces;

import android.support.design.widget.CoordinatorLayout;

import hackwestern.hack.com.hackwestern.getstarted.parsers.LoginRequestDataParser;
import hackwestern.hack.com.hackwestern.getstarted.parsers.LoginResponseParser;

/**
 * Created by Sarthak on 18-11-2017
 */
public interface EmailLoginContract {
    interface View {
        void init();

        void setLoginDetailsEnabled(boolean show);

        CoordinatorLayout getParentView();

        void showHomeScreen();

        void showProgressBar(boolean show);
    }

    interface Presenter {

        void callLogInApi(LoginRequestDataParser requestDataParser);

        void onEmailLoginFailure(String message);

        void onEmailLoginSuccess(LoginResponseParser responseParser);
    }
}
