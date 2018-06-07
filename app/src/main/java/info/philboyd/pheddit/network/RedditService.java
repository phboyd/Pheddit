package info.philboyd.pheddit.network;

import info.philboyd.pheddit.model.RedditParent;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Used to pull data from Reddit
 */
public interface RedditService {

    /**
     * This method is used to data from reddit
     * subreddit can be left blank for front page
     * after can be left blank for top of page
     * @param subreddit
     * @param count
     * @param after
     * @return
     */
    @GET("{subreddit}/.json?app=res")
    Observable<RedditParent> getRedditParent(
            @Path("subreddit") String subreddit,
            @Query("count") int count,
            @Query("after") String after);
}
