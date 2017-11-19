package hackwestern.hack.com.hackwestern.getstarted.fragments;

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
import hackwestern.hack.com.hackwestern.getstarted.interfaces.EmailSignUpContract;
import hackwestern.hack.com.hackwestern.getstarted.parsers.SignupRequestDataParser;
import hackwestern.hack.com.hackwestern.getstarted.presenters.EmailSignupPresenter;
import hackwestern.hack.com.hackwestern.homescreen.activities.HomeScreenActivity;
import hackwestern.hack.com.hackwestern.utils.Utils;
import hackwestern.hack.com.hackwestern.widgets.AppEditText;

/**
 * Created by Sarthak on 18-11-2017
 */
public class EmailSignUpFragment extends android.support.v4.app.Fragment implements EmailSignUpContract.View {

    @Bind(R.id.etName)
    AppEditText editTextName;
    @Bind(R.id.etEmail)
    AppEditText editTextEmail;
    @Bind(R.id.etPassword)
    AppEditText editTextPassword;
    @Bind(R.id.etTwitterUsername)
    AppEditText editTextTwitter;
    @Bind(R.id.parentViewSignUp)
    CoordinatorLayout parentView;
    @Bind(R.id.layoutProgressBar)
    LinearLayout progressBar;

    private EmailSignUpContract.Presenter presenter;

    public EmailSignUpFragment() {
        // Required empty public constructor
    }

    public static EmailSignUpFragment newInstance() {
        Bundle args = new Bundle();
        EmailSignUpFragment signUpFragment = new EmailSignUpFragment();
        return signUpFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email_signup, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void init() {
        presenter = new EmailSignupPresenter(this);
        ((BaseActivity) getActivity()).getApiComponent().inject((EmailSignupPresenter) presenter);
    }

    @Override
    public CoordinatorLayout getParentView() {
        if (parentView != null) {
            return parentView;
        }
        return null;
    }

    @Override
    public void setSignUpDetailsEnabled(boolean show) {
        editTextName.setEnabled(show);
        editTextEmail.setEnabled(show);
        editTextPassword.setEnabled(show);
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
            }
        });
    }

    /*
        Go back to the Email Login Screen
     */
    @OnClick(R.id.imageViewAlreadyRegistered)
    public void onClickAlreadyRegistered() {
        getActivity().onBackPressed();
    }

    /*
        Sign up user
     */
    @OnClick(R.id.buttonEmailSignUp)
    public void onClickSignUpButton() {
        if (validateSignUp()) {
            SignupRequestDataParser requestDataParser = new SignupRequestDataParser(editTextName.getText().toString(),
                    editTextEmail.getText().toString(), editTextPassword.getText().toString(), editTextTwitter.getText().toString());
            presenter.callCreateProfileApi(requestDataParser);
        }
    }

    private boolean validateSignUp() {

        if (editTextName.getText().toString().isEmpty() || editTextName.getText().toString().length() < 3) {
            Utils.showSnackBar(parentView, getString(R.string.string_name_length_error));
            return false;
        }

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
