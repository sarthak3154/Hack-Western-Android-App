package hackwestern.hack.com.hackwestern.firebase.adapters;

import android.content.Context;
import android.text.format.DateUtils;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import hackwestern.hack.com.hackwestern.firebase.model.ChatDataModel;

/**
 * Created by Sarthak on 18-11-2017
 */
public class ChatsAdapter extends FirebaseRecyclerAdapter<ChatDataModel, ChatViewHolder> {
    private Context context;
    private int lastAnimatedPosition = -1;

    /**
     * @param modelClass      Firebase will marshall the data at a location into
     *                        an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location,
     *                        using some combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public ChatsAdapter(Context context, Class<ChatDataModel> modelClass, int modelLayout, Class<ChatViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context = context;
    }

    @Override
    protected void populateViewHolder(ChatViewHolder viewHolder, ChatDataModel model, int position) {
        if (model.getSenderName() != null) {
            viewHolder.tvName.setText(model.getSenderName());
        }
        if (model.getText() != null)
            viewHolder.tvText.setText(model.getText());
        if (model.getPostedAt() != null)
            viewHolder.tvHours.setText(DateUtils.getRelativeTimeSpanString(model.getPostedAt()).toString());
    }
}
