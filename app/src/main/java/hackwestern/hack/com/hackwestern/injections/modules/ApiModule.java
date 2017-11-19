package hackwestern.hack.com.hackwestern.injections.modules;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hackwestern.hack.com.hackwestern.BuildSchemeConstants;
import hackwestern.hack.com.hackwestern.getstarted.interfaces.GetStartedWebServiceInterface;
import hackwestern.hack.com.hackwestern.homescreen.interfaces.HomeScreenWebServiceInterface;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by Sarthak on 18-11-2017
 */
@Module
public class ApiModule {
    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Provides
    @Singleton
    public OkHttpClient provideClient(HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(loggingInterceptor);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .build();

                return chain.proceed(request);
            }
        });
        builder.connectTimeout(120, TimeUnit.SECONDS);
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofitBuilder(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildSchemeConstants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public HomeScreenWebServiceInterface provideHomeScreenWebService(Retrofit retrofit) {
        return retrofit.create(HomeScreenWebServiceInterface.class);
    }

    @Provides
    @Singleton
    public GetStartedWebServiceInterface provideService(Retrofit retrofit) {
        return retrofit.create(GetStartedWebServiceInterface.class);
    }

}
