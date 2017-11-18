package hackwestern.hack.com.hackwestern.homescreen.model;

/**
 * Created by Sarthak on 18-11-2017
 */
public class ChatFeedDataModel {
    /**
     * Conversation Id of the chat between the two users
     */
    private String messageId;

    /**
     * Email of the other user chatting with
     */
    private String email;

    /**
     * Name of the other user chatting with
     */
    private String name;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
