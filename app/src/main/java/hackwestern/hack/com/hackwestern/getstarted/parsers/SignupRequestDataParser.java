package hackwestern.hack.com.hackwestern.getstarted.parsers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sarthak on 18-11-2017
 */
public class SignupRequestDataParser {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("twitterUserName")
    @Expose
    private String twitterUserName;

    public SignupRequestDataParser(String name, String email, String password, String twitterUserName) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.twitterUserName = twitterUserName;
    }


}
