package hackwestern.hack.com.hackwestern.homescreen.parsers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sarthak on 18-11-2017
 */
public class ChatsResponseParser {

    @SerializedName("message_id")
    @Expose
    private String messageId;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("name")
    @Expose
    private String name;

    public String getMessageId() {
        return messageId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
