package hackwestern.hack.com.hackwestern.homescreen.interfaces;

import java.util.List;

import hackwestern.hack.com.hackwestern.homescreen.parsers.ChatsResponseParser;
import hackwestern.hack.com.hackwestern.homescreen.parsers.RequestChatRequestParser;
import hackwestern.hack.com.hackwestern.homescreen.parsers.RequestChatResponseParser;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Sarthak on 18-11-2017
 */
public interface HomeScreenWebServiceInterface {

    String PARAM_PATH_EMAIL = "email";
    String QUERY_EMAIL = "email";

    @GET("chat/all")
    Observable<List<ChatsResponseParser>> getUserAllChats(@Query(QUERY_EMAIL) String email);

    @POST
    Observable<RequestChatResponseParser> requestChat(@Body RequestChatRequestParser requestParser);
}
