package hackwestern.hack.com.hackwestern.homescreen.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.Bind;
import butterknife.ButterKnife;
import hackwestern.hack.com.hackwestern.R;
import hackwestern.hack.com.hackwestern.homescreen.interfaces.CommentsScreenContract;
import hackwestern.hack.com.hackwestern.widgets.SwipeBackActivity;

/**
 * Created by Sarthak on 18-11-2017
 */
public class CommentsActivity extends SwipeBackActivity implements CommentsScreenContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        init();
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
    }
}
