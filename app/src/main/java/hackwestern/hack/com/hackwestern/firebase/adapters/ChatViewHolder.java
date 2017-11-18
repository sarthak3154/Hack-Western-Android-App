package hackwestern.hack.com.hackwestern.firebase.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import hackwestern.hack.com.hackwestern.R;
import hackwestern.hack.com.hackwestern.widgets.AppTextView;

/**
 * Created by Sarthak on 18-11-2017
 */
public class ChatViewHolder extends RecyclerView.ViewHolder {

    AppTextView tvText;
    AppTextView tvName;
    AppTextView tvHours;

    public ChatViewHolder(View itemView) {
        super(itemView);
        tvText = (AppTextView) itemView.findViewById(R.id.tvChatText);
        tvName = (AppTextView) itemView.findViewById(R.id.tvName);
        tvHours = (AppTextView) itemView.findViewById(R.id.tvHours);

    }
}
