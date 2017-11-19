package hackwestern.hack.com.hackwestern.homescreen.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hackwestern.hack.com.hackwestern.BaseActivity;
import hackwestern.hack.com.hackwestern.R;
import hackwestern.hack.com.hackwestern.dbmodels.UserProfileData;
import hackwestern.hack.com.hackwestern.homescreen.interfaces.RequestChatScreenContract;
import hackwestern.hack.com.hackwestern.homescreen.parsers.RequestChatRequestParser;
import hackwestern.hack.com.hackwestern.homescreen.presenters.RequestChatPresenter;
import hackwestern.hack.com.hackwestern.widgets.AppButton;

/**
 * Created by Sarthak on 19-11-2017
 */
public class RequestChatActivity extends BaseActivity implements RequestChatScreenContract.View {
    private static final int TARGET_USER = 0;
    private static final int TARGET_VOLUNTEER = 1;
    private static final int TARGET_THERAPIST = 2;
    private static final String KEY_CONVERSATION_ID = "conversation_id";
    private static final String KEY_EMAIL = "recipientEmail";
    private static final String KEY_NAME = "recipientName";

    @Bind(R.id.buttonUserChat)
    AppButton btnUserChat;
    @Bind(R.id.buttonVolunteerChat)
    AppButton btnVolunteerChat;
    @Bind(R.id.buttonTherapist)
    AppButton btnTherapistChat;
    @Bind(R.id.layoutProgressBar)
    LinearLayout progressBar;

    private RequestChatRequestParser chatRequestParser;
    private RequestChatScreenContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_chat);
        init();
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        presenter = new RequestChatPresenter(this);
        getApiComponent().inject((RequestChatPresenter) presenter);
        UserProfileData userProfileData = UserProfileData.getUserData();
        if (userProfileData != null) {
            chatRequestParser = new RequestChatRequestParser(userProfileData.getEmail());
        }
    }

    @Override
    public void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void startChatScreen(String conversationId, String emailId, String userName) {
        Log.i("ChatScreen", conversationId + " ," + emailId + " ," + userName);
        Intent intent = new Intent(RequestChatActivity.this, ChatScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(KEY_CONVERSATION_ID, conversationId);
        intent.putExtra(KEY_EMAIL, emailId);
        intent.putExtra(KEY_NAME, userName);
        startActivity(intent);
    }

    @OnClick(R.id.buttonUserChat)
    public void onClickUserChatButton() {
        chatRequestParser.setTarget(TARGET_USER);
        presenter.callRequestChatApi(chatRequestParser);
    }

    @OnClick(R.id.buttonVolunteerChat)
    public void onClickVolunteerChatButton() {
        chatRequestParser.setTarget(TARGET_VOLUNTEER);
        presenter.callRequestChatApi(chatRequestParser);
    }

    @OnClick(R.id.buttonTherapist)
    public void onClickTherapistChatButton() {
        chatRequestParser.setTarget(TARGET_THERAPIST);
        presenter.callRequestChatApi(chatRequestParser);
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        super.onBackPressed();
    }
}

