package hackwestern.hack.com.hackwestern.getstarted.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hackwestern.hack.com.hackwestern.BaseActivity;
import hackwestern.hack.com.hackwestern.R;
import hackwestern.hack.com.hackwestern.getstarted.interfaces.EmailLoginContract;
import hackwestern.hack.com.hackwestern.getstarted.interfaces.EmailSignUpContract;
import hackwestern.hack.com.hackwestern.getstarted.parsers.LoginRequestDataParser;
import hackwestern.hack.com.hackwestern.getstarted.presenters.EmailLoginPresenter;
import hackwestern.hack.com.hackwestern.homescreen.activities.HomeScreenActivity;
import hackwestern.hack.com.hackwestern.utils.Utils;
import hackwestern.hack.com.hackwestern.widgets.AppEditText;

/**
 * Created by Sarthak on 18-11-2017
 */

public class EmailLoginFragment extends android.support.v4.app.Fragment implements EmailLoginContract.View {

    @Bind(R.id.etLoginEmail)
    AppEditText editTextEmail;
    @Bind(R.id.etLoginPassword)
    AppEditText editTextPassword;
    @Bind(R.id.parentViewLogin)
    CoordinatorLayout parentView;
    @Bind(R.id.layoutProgressBar)
    LinearLayout progressBar;


    private EmailSignUpContract.View signUpView;
    private EmailLoginContract.Presenter emailLoginPresenter;
    private static final String SIGN_UP_SCREEN = "sign_up_screen";

    public EmailLoginFragment() {

    }

    public static EmailLoginFragment newInstance() {
        Bundle args = new Bundle();
        EmailLoginFragment fragment = new EmailLoginFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void init() {
        emailLoginPresenter = new EmailLoginPresenter(this);
        ((BaseActivity) getActivity()).getApiComponent().inject((EmailLoginPresenter) emailLoginPresenter);
    }

    @Override
    public void setLoginDetailsEnabled(boolean show) {
        editTextEmail.setEnabled(show);
        editTextPassword.setEnabled(show);
    }

    @Override
    public CoordinatorLayout getParentView() {
        if (parentView != null) return parentView;
        return null;
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showHomeScreen() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getActivity(), HomeScreenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });

    }

    @OnClick(R.id.buttonEmailLogin)
    public void onClickEmailLogin() {
        if (validateLogin()) {
            setLoginDetailsEnabled(false);
            LoginRequestDataParser requestDataParser = new LoginRequestDataParser(editTextEmail.getText().toString(),
                    editTextPassword.getText().toString());
            emailLoginPresenter.callLogInApi(requestDataParser);
        }
    }

    /*
        Initializing the EmailSignUpFragment with animations, to display the Sign Up Screen
     */
    @OnClick(R.id.imageViewSignUp)
    public void onClickSignUpMove() {
        signUpView = EmailSignUpFragment.newInstance();
        getFragmentManager().beginTransaction().setCustomAnimations(R.anim.push_up_in, 0, R.anim.push_down_out, 0)
                .replace(R.id.signUpFrameLayout, (EmailSignUpFragment) signUpView)
                .addToBackStack(SIGN_UP_SCREEN)
                .commit();
    }

    public boolean validateLogin() {

        if (editTextEmail.getText().toString().isEmpty() ||
                !Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString()).matches()) {
            Utils.showSnackBar(parentView, getString(R.string.string_email_format_error));
            return false;
        }

        if (editTextPassword.getText().toString().isEmpty() || editTextPassword.getText().toString().length() < 6) {
            Utils.showSnackBar(parentView, getString(R.string.string_password_length_error));
            return false;
        }

        return true;
    }
}
