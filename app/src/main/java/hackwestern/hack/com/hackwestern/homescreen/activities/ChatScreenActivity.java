package hackwestern.hack.com.hackwestern.homescreen.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hackwestern.hack.com.hackwestern.R;
import hackwestern.hack.com.hackwestern.dbmodels.UserProfileData;
import hackwestern.hack.com.hackwestern.firebase.adapters.ChatViewHolder;
import hackwestern.hack.com.hackwestern.firebase.adapters.ChatsAdapter;
import hackwestern.hack.com.hackwestern.firebase.model.ChatDataModel;
import hackwestern.hack.com.hackwestern.homescreen.interfaces.ChatScreenContract;
import hackwestern.hack.com.hackwestern.homescreen.presenters.ChatScreenPresenter;
import hackwestern.hack.com.hackwestern.widgets.AppButton;
import hackwestern.hack.com.hackwestern.widgets.AppEditText;
import hackwestern.hack.com.hackwestern.widgets.SwipeBackActivity;

/**
 * Created by Sarthak on 18-11-2017
 */
public class ChatScreenActivity extends SwipeBackActivity implements ChatScreenContract.View {

    @Bind(R.id.btnSendMessage)
    AppButton btnSendMessage;
    @Bind(R.id.etMessage)
    AppEditText etMessage;
    @Bind(R.id.rvChats)
    RecyclerView rvChats;
    @Bind(R.id.toolbarChat)
    protected Toolbar toolbar;
    @Bind(R.id.layoutProgressBar)
    LinearLayout progressBar;

    private static final String CONVERSATION_ID = "conversation_id";
    private static final String CONVERSATION_CHILD = "conversations";
    private static final String MESSAGES_CHILD = "messages";
    private static final String KEY_POSTED_AT = "postedAt";
    private static final String KEY_SENDER_EMAIL = "senderEmail";
    private static final String KEY_SENDER_NAME = "senderName";
    private static final String KEY_RECIPIENT_EMAIL = "recipientEmail";
    private static final String KEY_RECIPIENT_NAME = "recipientName";
    private static final String KEY_TEXT = "text";
    public static final int DEFAULT_COMMENTS_LENGTH_LIMIT = 100;

    private String conversationId;
    private String recipientName;
    private String recipientEmail;
    private Context context;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<ChatDataModel, ChatViewHolder> chatsAdapter;
    private ChatScreenContract.Presenter presenter;
    private Query queryRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        init();
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        UserProfileData.saveUserData("Sarthak", "sarthak.arora3154@gmail.com");
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(false);
            }
        }
        presenter = new ChatScreenPresenter(this);
        getApiComponent().inject((ChatScreenPresenter) presenter);
        progressBar.setVisibility(View.VISIBLE);
        if (getIntent() != null) {
            conversationId = getIntent().getStringExtra(CONVERSATION_ID);
            recipientName = getIntent().getStringExtra(KEY_RECIPIENT_NAME);
            recipientEmail = getIntent().getStringExtra(KEY_RECIPIENT_EMAIL);
        }

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rvChats.setLayoutManager(linearLayoutManager);
        context = this;

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        conversationId = "23423rsfsf34";

        Query query = mFirebaseDatabaseReference.child(CONVERSATION_CHILD).child(conversationId).child(MESSAGES_CHILD);
        queryRef = query.orderByChild(KEY_POSTED_AT);

        UserProfileData userProfileData = UserProfileData.getUserData();
        if (userProfileData != null)
            mFirebaseDatabaseReference.child(CONVERSATION_CHILD).child(conversationId).child(MESSAGES_CHILD).child(KEY_SENDER_EMAIL).setValue(userProfileData.getEmail());
        mFirebaseDatabaseReference.child(CONVERSATION_CHILD).child(conversationId).child(MESSAGES_CHILD).child(KEY_RECIPIENT_EMAIL).setValue(recipientEmail);

        chatsAdapter = new ChatsAdapter(this, ChatDataModel.class, R.layout.item_chat_message, ChatViewHolder.class, queryRef);
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        chatsAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                progressBar.setVisibility(View.GONE);
                int commentMessageCount = chatsAdapter.getItemCount();
                int lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.

                if (lastVisiblePosition == -1 ||
                        (positionStart >= (commentMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    rvChats.scrollToPosition(positionStart);
                }
            }

            @Override
            public void onChanged() {
                super.onChanged();
                progressBar.setVisibility(View.GONE);
            }
        });

        rvChats.setAdapter(chatsAdapter);
        etMessage.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_COMMENTS_LENGTH_LIMIT)});
        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    btnSendMessage.setEnabled(true);
                } else {
                    btnSendMessage.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @OnClick(R.id.btnSendMessage)
    public void onClickSendMessage() {
        UserProfileData userProfileData = UserProfileData.getUserData();
        if (userProfileData != null) {
            Map<String, Object> message = new HashMap<>();
            message.put(KEY_SENDER_NAME, userProfileData.getName());
            message.put(KEY_RECIPIENT_NAME, recipientName);
            message.put(KEY_SENDER_EMAIL, userProfileData.getEmail());
            message.put(KEY_RECIPIENT_EMAIL, recipientEmail);
            message.put(KEY_POSTED_AT, ServerValue.TIMESTAMP);
            message.put(KEY_TEXT, etMessage.getText().toString());
            mFirebaseDatabaseReference.child(CONVERSATION_CHILD).child(conversationId).child(MESSAGES_CHILD).push().setValue(message);
            etMessage.setText("");
        }
    }

}
