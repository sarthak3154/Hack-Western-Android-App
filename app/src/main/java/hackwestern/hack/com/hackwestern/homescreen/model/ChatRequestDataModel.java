package hackwestern.hack.com.hackwestern.homescreen.model;

/**
 * Created by Sarthak on 18-11-2017
 */
public class ChatRequestDataModel {
    private String conversationId;
    private String userName;
    private String user2Name;
    private String userEmail;
    private String user2Email;
    private int status1;
    private int status2;

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUser2Name() {
        return user2Name;
    }

    public void setUser2Name(String user2Name) {
        this.user2Name = user2Name;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUser2Email() {
        return user2Email;
    }

    public void setUser2Email(String user2Email) {
        this.user2Email = user2Email;
    }

    public int getStatus1() {
        return status1;
    }

    public void setStatus1(int status1) {
        this.status1 = status1;
    }

    public int getStatus2() {
        return status2;
    }

    public void setStatus2(int status2) {
        this.status2 = status2;
    }
}
