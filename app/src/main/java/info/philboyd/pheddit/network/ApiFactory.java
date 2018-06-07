package info.philboyd.pheddit.network;

import info.philboyd.pheddit.network.RedditService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by philb on 6/6/2018.
 */

public class ApiFactory {

    private static String URL = "https://reddit.com/";

    public static RedditService createRedditService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RedditService redditService = retrofit.create(RedditService.class);
        return redditService;

    }
}
