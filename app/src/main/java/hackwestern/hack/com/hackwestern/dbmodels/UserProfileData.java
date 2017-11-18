package hackwestern.hack.com.hackwestern.dbmodels;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Sarthak on 18-11-2017
 */
@Table(name = "user_profile_data")
public class UserProfileData extends Model {

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    public UserProfileData() {
        super();
    }

    public UserProfileData(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static UserProfileData getUserData() {
        List<UserProfileData> userData = new Select().from(UserProfileData.class).execute();
        if (userData != null && !userData.isEmpty()) {
            return userData.get(0);
        }
        return null;
    }

    public static void saveUserData(String name, String email) {
        if (name != null && email != null) {
            deleteUserData();
            UserProfileData userProfileData = new UserProfileData(name, email);
            userProfileData.save();
        }
    }

    public static void deleteUserData() {
        new Delete().from(UserProfileData.class).execute();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
