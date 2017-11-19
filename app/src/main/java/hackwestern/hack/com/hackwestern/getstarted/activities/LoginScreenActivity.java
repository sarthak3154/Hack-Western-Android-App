package hackwestern.hack.com.hackwestern.getstarted.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import hackwestern.hack.com.hackwestern.BaseActivity;
import hackwestern.hack.com.hackwestern.R;
import hackwestern.hack.com.hackwestern.getstarted.LoginScreenContract;
import hackwestern.hack.com.hackwestern.getstarted.fragments.EmailLoginFragment;

/**
 * Created by Lenovo on 18-11-2017
 */
public class LoginScreenActivity extends BaseActivity implements LoginScreenContract.View {
    private static final String LOGIN_SCREEN = "login_screen";
    private static final String SIGN_UP_SCREEN = "sign_up_screen";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        init();
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        loginView = EmailLoginFragment.newInstance();
    }
}
