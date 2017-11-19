package hackwestern.hack.com.hackwestern.getstarted.parsers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sarthak on 18-11-2017
 */
public class LoginResponseParser {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("email")
    @Expose
    private String email;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
