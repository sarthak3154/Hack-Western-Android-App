package hackwestern.hack.com.hackwestern.getstarted.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import hackwestern.hack.com.hackwestern.BaseActivity;
import hackwestern.hack.com.hackwestern.R;
import hackwestern.hack.com.hackwestern.dbmodels.UserPreferencesData;
import hackwestern.hack.com.hackwestern.getstarted.interfaces.LoginScreenContract;
import hackwestern.hack.com.hackwestern.getstarted.fragments.EmailLoginFragment;
import hackwestern.hack.com.hackwestern.getstarted.interfaces.EmailLoginContract;
import hackwestern.hack.com.hackwestern.homescreen.activities.HomeScreenActivity;
import hackwestern.hack.com.hackwestern.widgets.AppTextView;

/**
 * Created by Sarthak on 18-11-2017
 */
public class LoginScreenActivity extends BaseActivity implements LoginScreenContract.View {
    private static final String LOGIN_SCREEN = "login_screen";
    private static final String SIGN_UP_SCREEN = "sign_up_screen";
    private static final int SPLASH_SCREEN_DURATION = 1500;

    @Bind(R.id.textVibes)
    AppTextView vibesText;

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
        UserPreferencesData userPreferencesData = UserPreferencesData.getUserPreferencesData();
        if (userPreferencesData.isUserLoggedIn()) {
            moveToHomeScreen();
        } else {
            vibesText.setVisibility(View.GONE);
            loginView = EmailLoginFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(0, 0, R.anim.push_up_in, R.anim.push_down_out)
                    .replace(R.id.loginFrameLayout, (EmailLoginFragment) loginView)
                    .addToBackStack(LOGIN_SCREEN)
                    .commit();
        }
    }

    @Override
    public void moveToHomeScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isFinishing()) {
                    Intent intent = new Intent(LoginScreenActivity.this, HomeScreenActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_SCREEN_DURATION);
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
