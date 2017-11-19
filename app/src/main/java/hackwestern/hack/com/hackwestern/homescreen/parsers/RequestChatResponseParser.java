package hackwestern.hack.com.hackwestern.homescreen.parsers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sarthak on 18-11-2017
 */
public class RequestChatResponseParser {


    @SerializedName("message_id")
    @Expose
    private String messageId;

    @SerializedName("user1Name")
    @Expose
    private String user1Name;

    @SerializedName("user2Name")
    @Expose
    private String user2Name;
    @SerializedName("user1Email")
    @Expose
    private String user1Email;

    @SerializedName("user2Email")
    @Expose
    private String user2Email;

    @SerializedName("status1")
    @Expose
    private int status1;

    @SerializedName("status2")
    @Expose
    private int status2;

    public String getMessageId() {
        return messageId;
    }

    public String getUser1Name() {
        return user1Name;
    }

    public String getUser2Name() {
        return user2Name;
    }

    public String getUser1Email() {
        return user1Email;
    }

    public String getUser2Email() {
        return user2Email;
    }

    public int getStatus1() {
        return status1;
    }

    public int getStatus2() {
        return status2;
    }
}
