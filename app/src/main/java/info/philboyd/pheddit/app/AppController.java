package info.philboyd.pheddit.app;

import android.app.Application;
import android.content.Context;

import info.philboyd.pheddit.network.ApiFactory;
import info.philboyd.pheddit.network.RedditService;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by philb on 6/6/2018.
 */

public class AppController extends Application {

    private RedditService redditService;
    private Scheduler scheduler;

    private static AppController get(Context context) {
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }

    public RedditService getRedditService() {
        if(redditService == null) {
            redditService = ApiFactory.createRedditService();
        }
        return redditService;
    }

    public Scheduler getScheduler() {
        if(scheduler == null) {
            scheduler = Schedulers.io();
        }
        return scheduler;
    }

    public void setRedditService(RedditService redditService) {
        this.redditService = redditService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

}
