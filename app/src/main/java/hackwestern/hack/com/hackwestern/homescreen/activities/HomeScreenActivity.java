package hackwestern.hack.com.hackwestern.homescreen.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hackwestern.hack.com.hackwestern.BaseActivity;
import hackwestern.hack.com.hackwestern.R;
import hackwestern.hack.com.hackwestern.firebase.model.ChatDataModel;
import hackwestern.hack.com.hackwestern.homescreen.adapters.ChatsFeedAdapter;
import hackwestern.hack.com.hackwestern.homescreen.interfaces.HomeScreenContract;
import hackwestern.hack.com.hackwestern.homescreen.model.ChatFeedDataModel;
import hackwestern.hack.com.hackwestern.homescreen.presenters.HomeScreenPresenter;

/**
 * Created by Sarthak on 18-11-2017
 */
public class HomeScreenActivity extends BaseActivity implements HomeScreenContract.View {

    @Bind(R.id.recyclerViewChats)
    protected RecyclerView recyclerViewChats;
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    private LinearLayoutManager linearLayoutManager;
    private List<ChatFeedDataModel> myChatsModelList = new ArrayList<>();
    private HomeScreenContract.Presenter presenter;
    private ChatsFeedAdapter feedAdapter;

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
    }
}
