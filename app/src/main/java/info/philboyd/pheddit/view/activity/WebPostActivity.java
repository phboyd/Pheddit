package info.philboyd.pheddit.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;

import java.util.Observable;
import java.util.Observer;

import info.philboyd.pheddit.R;
import info.philboyd.pheddit.databinding.ActivityPostWebBinding;
import info.philboyd.pheddit.view.adapter.PostAdapter;
import info.philboyd.pheddit.viewmodel.PostWebViewModel;
import info.philboyd.pheddit.viewmodel.PostsViewModel;

/**
 * Created by philb on 6/6/2018.
 */

public class WebPostActivity extends AppCompatActivity implements Observer {

    private ActivityPostWebBinding postWebActivityBinding;
    private PostWebViewModel postWebViewModel;

    private static final String WEB_URL = "WEB_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        postWebActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_web);
        //setSupportActionBar(postWebActivityBinding.toolbar);
        displayHomeAsUpEnabled();
        setUpObserver(postWebViewModel);
        getExtrasFromIntent();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof PostWebViewModel) {
            WebView webView = (WebView) postWebActivityBinding.webview;
            PostWebViewModel postsWebViewModel = (PostWebViewModel) o;
            webView.loadUrl(postsWebViewModel.getWebUrl());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    public static Intent fillWebUrl(Context context, String redditPostUrl) {
        Intent intent = new Intent(context, WebPostActivity.class);
        intent.putExtra(WEB_URL, redditPostUrl);
        return intent;
    }


    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

    private void getExtrasFromIntent(){
        String url = (String) getIntent().getSerializableExtra(WEB_URL);
        postWebViewModel.setWebUrl(url);
        postWebActivityBinding.setPostWebViewModel(postWebViewModel);
    }

    private void initDataBinding() {
        postWebViewModel = new PostWebViewModel(this);
    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
