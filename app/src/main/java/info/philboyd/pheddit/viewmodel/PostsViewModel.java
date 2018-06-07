package info.philboyd.pheddit.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import info.philboyd.pheddit.app.AppController;
import info.philboyd.pheddit.model.RedditChild;
import info.philboyd.pheddit.model.RedditParent;
import info.philboyd.pheddit.model.RedditPost;
import info.philboyd.pheddit.network.RedditService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class PostsViewModel extends Observable implements OnBottomReachedListener {


    public ObservableInt progressBar;
    public ObservableInt postRecycler;

    private List<RedditPost> postList;
    private String subreddit;
    private String postName;
    private String subredditSearchString;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PostsViewModel(@NonNull Context context) {
        this.context = context;
        this.postList = new ArrayList<>();
        this.subreddit = "";
        this.postName = "";
        this.subredditSearchString = "";
        progressBar = new ObservableInt(View.GONE);
        postRecycler = new ObservableInt(View.GONE);
    }

    public void load() {
        initializeViews();
        fetchPostList();
    }

    public void initializeViews() {
        postRecycler.set(View.GONE);
        progressBar.set(View.VISIBLE);
    }


    @Override
    public void onBottomReached() {
        fetchPostList();
    }

    public List<RedditPost> getPostList() {
        return postList;
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }

    public void searchSubreddit(View view) {
        this.setSubreddit(this.subredditSearchString);
        this.initializeViews();
        this.fetchPostList();
    }

    public void setSubreddit(String subreddit) {
        this.postList.clear();
        if(!subreddit.startsWith("r/") && !subreddit.equals("")) {
            this.subreddit = "r/" + subreddit + "/";
        } else {
            this.subreddit = "";
        }
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getSubredditSearchString() {
        return subredditSearchString;
    }

    public void setSubredditSearchString(String subredditSearchString) {
        this.subredditSearchString = subredditSearchString;
    }

    private void fetchPostList() {
        AppController appController = AppController.create(context);
        RedditService redditService = appController.getRedditService();

        String queryLastPostName = this.generateQueryString();
        Disposable disposable = redditService.getRedditParent(this.subreddit, this.postList.size(), queryLastPostName)
                .subscribeOn(appController.getScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RedditParent>() {
                    @Override public void accept(RedditParent redditParentResponse) throws Exception {
                        updateUserDataList(redditParentResponse.getData().getChildren());
                        progressBar.set(View.GONE);
                        postRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception {
                        progressBar.set(View.GONE);
                        postRecycler.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private String generateQueryString() {
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("");
        if(this.postList.size() != 0) {
            queryStringBuilder.append(this.postList.get(this.postList.size()-1).getName());
        }
        return queryStringBuilder.toString();
    }

    private void updateUserDataList(List<RedditChild> children) {
        for(RedditChild child: children) {
            postList.add(child.getData());
        }
        setChanged();
        notifyObservers();
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
