package info.philboyd.pheddit.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Observable;
import java.util.Observer;

import info.philboyd.pheddit.R;
import info.philboyd.pheddit.databinding.ActivityPostsBinding;
import info.philboyd.pheddit.view.adapter.PostAdapter;
import info.philboyd.pheddit.viewmodel.PostsViewModel;

public class PostsActivity extends AppCompatActivity implements Observer {

    private ActivityPostsBinding postActivityBinding;
    private PostsViewModel postsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(postActivityBinding.toolbar);
        setUpListOfPostsView(postActivityBinding.listPost);
        setUpObserver(postsViewModel);
        postsViewModel.load();
    }


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof  PostsViewModel) {
            PostAdapter postsAdapter = (PostAdapter) postActivityBinding.listPost.getAdapter();
            PostsViewModel postsViewModel = (PostsViewModel) o;
            postsAdapter.setPostList(postsViewModel.getPostList());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        postsViewModel.reset();
    }


    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

    private void initDataBinding() {
        postActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_posts);
        postsViewModel = new PostsViewModel(this);
        postActivityBinding.setPostViewModel(postsViewModel);
    }

    private void setUpListOfPostsView(RecyclerView recyclerView) {
        PostAdapter postAdapter = new PostAdapter();
        postAdapter.setOnBottomReachedListener(postsViewModel);
        recyclerView.setAdapter(postAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
