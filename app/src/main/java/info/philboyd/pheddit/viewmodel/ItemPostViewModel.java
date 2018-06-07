package info.philboyd.pheddit.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import info.philboyd.pheddit.model.RedditPost;
import info.philboyd.pheddit.view.activity.WebPostActivity;

/**
 * Created by philb on 6/6/2018.
 */

public class ItemPostViewModel extends BaseObservable {
    public RedditPost redditPost;
    private Context context;

    public ItemPostViewModel(RedditPost redditPost, Context context)  {
        this.redditPost = redditPost;
        this.context = context;
    }

    public void onItemClick(View view) {
        context.startActivity(WebPostActivity.fillWebUrl(view.getContext(), redditPost.getUrl()));
    }

    public void setRedditPost(RedditPost redditPost) {
        this.redditPost = redditPost;
        notifyChange();
    }

}
