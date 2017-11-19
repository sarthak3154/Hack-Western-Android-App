package hackwestern.hack.com.hackwestern.homescreen.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hackwestern.hack.com.hackwestern.BaseActivity;
import hackwestern.hack.com.hackwestern.R;
import hackwestern.hack.com.hackwestern.dbmodels.UserProfileData;
import hackwestern.hack.com.hackwestern.firebase.model.ChatDataModel;
import hackwestern.hack.com.hackwestern.homescreen.adapters.ChatsFeedAdapter;
import hackwestern.hack.com.hackwestern.homescreen.interfaces.HomeScreenContract;
import hackwestern.hack.com.hackwestern.homescreen.model.ChatFeedDataModel;
import hackwestern.hack.com.hackwestern.homescreen.presenters.HomeScreenPresenter;

/**
 * Created by Sarthak on 18-11-2017
 */
public class HomeScreenActivity extends BaseActivity implements HomeScreenContract.View, ChatsFeedAdapter.OnFeedItemClickListener {

    @Bind(R.id.recyclerViewChats)
    protected RecyclerView recyclerViewChats;
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.layoutProgressBar)
    LinearLayout progressBar;

    private LinearLayoutManager linearLayoutManager;
    private List<ChatFeedDataModel> myChatsModelList = new ArrayList<>();
    private HomeScreenContract.Presenter presenter;
    private ChatsFeedAdapter feedAdapter;

    private static final String KEY_CONVERSATION_ID = "conversation_id";
    private static final String KEY_EMAIL = "recipientEmail";
    private static final String KEY_NAME = "recipientName";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        init();
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        presenter = new HomeScreenPresenter(this);
        getApiComponent().inject((HomeScreenPresenter) presenter);
        feedAdapter = new ChatsFeedAdapter(this, myChatsModelList);
        feedAdapter.setOnFeedItemClickListener(this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewChats.setLayoutManager(linearLayoutManager);
        recyclerViewChats.setAdapter(feedAdapter);
    }

    @Override
    protected void onResume() {
        if (!isViewDestroyed()) {
            UserProfileData userProfileData = UserProfileData.getUserData();
            if (userProfileData != null) {
                presenter.fetchUserConversations(userProfileData.getEmail());
            }
        }
        super.onResume();
    }

    @Override
    public boolean isViewDestroyed() {
        return isFinishing();
    }

    @Override
    public void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showTextNoChatRoom(boolean show) {

    }

    @Override
    public void showMyChats(List<ChatFeedDataModel> chatsModelList) {
        myChatsModelList.clear();
        for (ChatFeedDataModel model : chatsModelList) {
            myChatsModelList.add(model);
        }
        showProgressBar(false);
        feedAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btnNewChat)
    public void startNewChat() {
        Intent intent = new Intent(HomeScreenActivity.this, RequestChatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    public void onItemClicked(String conversationId, String email, String name) {
        Intent intent = new Intent(HomeScreenActivity.this, ChatScreenActivity.class);
        intent.putExtra(KEY_CONVERSATION_ID, conversationId);
        intent.putExtra(KEY_EMAIL, email);
        intent.putExtra(KEY_NAME, name);
        startActivity(intent);
    }
}
