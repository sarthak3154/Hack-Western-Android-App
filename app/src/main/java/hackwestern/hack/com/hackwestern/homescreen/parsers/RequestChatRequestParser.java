package hackwestern.hack.com.hackwestern.homescreen.parsers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sarthak on 19-11-2017
 */
public class RequestChatRequestParser {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("target")
    @Expose
    private int target;

    public RequestChatRequestParser(String email) {
        this.email = email;
    }

    public RequestChatRequestParser(String email, int target) {
        this.email = email;
        this.target = target;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}

