package hackwestern.hack.com.hackwestern.getstarted.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import hackwestern.hack.com.hackwestern.BaseActivity;
import hackwestern.hack.com.hackwestern.R;
import hackwestern.hack.com.hackwestern.getstarted.LoginScreenContract;
import hackwestern.hack.com.hackwestern.getstarted.fragments.EmailLoginFragment;
import hackwestern.hack.com.hackwestern.getstarted.interfaces.EmailLoginContract;

/**
 * Created by Sarthak on 18-11-2017
 */
public class LoginScreenActivity extends BaseActivity implements LoginScreenContract.View {
    private static final String LOGIN_SCREEN = "login_screen";
    private static final String SIGN_UP_SCREEN = "sign_up_screen";

    private EmailLoginContract.View loginView;

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
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(0, 0, R.anim.push_up_in, R.anim.push_down_out)
                .replace(R.id.loginFrameLayout, (EmailLoginFragment) loginView)
                .addToBackStack(LOGIN_SCREEN)
                .commit();

    }

    @Override
    public void onBackPressed() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
