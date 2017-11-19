package hackwestern.hack.com.hackwestern.getstarted.interfaces;

import hackwestern.hack.com.hackwestern.getstarted.parsers.LoginRequestDataParser;
import hackwestern.hack.com.hackwestern.getstarted.parsers.LoginResponseParser;
import hackwestern.hack.com.hackwestern.getstarted.parsers.SignupRequestDataParser;
import hackwestern.hack.com.hackwestern.getstarted.parsers.SignupResponseParser;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Sarthak on 18-11-2017
 */
public interface GetStartedWebServiceInterface {

    @POST("login")
    Observable<Response<LoginResponseParser>> signIn(@Body LoginRequestDataParser requestDataParser);

    @POST("signup")
    Observable<Response<SignupResponseParser>> signUp(@Body SignupRequestDataParser requestDataParser);
}
