package info.philboyd.pheddit.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import info.philboyd.pheddit.R;
import info.philboyd.pheddit.databinding.ItemPostBinding;
import info.philboyd.pheddit.model.RedditPost;
import info.philboyd.pheddit.viewmodel.OnBottomReachedListener;
import info.philboyd.pheddit.viewmodel.ItemPostViewModel;

/**
 * Created by philb on 6/6/2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostAdapterViewHolder> {


    private List<RedditPost> postList;
    private OnBottomReachedListener onBottomReachedListener;

    public PostAdapter() {
        this.postList = Collections.emptyList();
    }

    @Override
    public PostAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemPostBinding itemPostBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_post ,parent, false);
        return new PostAdapterViewHolder(itemPostBinding);
    }

    @Override
    public void onBindViewHolder(PostAdapterViewHolder holder, int position) {
        if(position == this.postList.size() -1) {
            onBottomReachedListener.onBottomReached();
        }
        holder.bindPost(postList.get(position));
    }

    @Override
    public int getItemCount() {
        return  postList.size();
    }

    public void setPostList(List<RedditPost> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener;
    }

    public static class PostAdapterViewHolder extends RecyclerView.ViewHolder {

        ItemPostBinding mItemPostBinding;

        public PostAdapterViewHolder(ItemPostBinding itemPostBinding) {
            super(itemPostBinding.itemSubmission);
            this.mItemPostBinding = itemPostBinding;
        }

        void bindPost(RedditPost post){
            if(mItemPostBinding.getPostViewModel() == null){
                mItemPostBinding.setPostViewModel(new ItemPostViewModel(post, itemView.getContext()));
                mItemPostBinding.setPostViewModel(new ItemPostViewModel(post, itemView.getContext()));
            } else {
                mItemPostBinding.getPostViewModel().setRedditPost(post);
            }
        }
    }

}
