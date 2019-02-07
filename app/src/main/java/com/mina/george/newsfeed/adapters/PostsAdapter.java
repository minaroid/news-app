package com.mina.george.newsfeed.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mina.george.newsfeed.R;
import com.mina.george.newsfeed.di.scope.ActivityScope;
import com.mina.george.newsfeed.store.models.article.ArticleModel;
import com.mina.george.newsfeed.ui.postactivity.PostActivity;
import com.mina.george.newsfeed.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

@ActivityScope
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<ArticleModel> postModels = new ArrayList<>();
    private OnBottomReachedListener onBottomReachedListener;

    @Inject
    PostsAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == postModels.size() - 1 && onBottomReachedListener != null) {
            onBottomReachedListener.loadMore();
        }
        holder.bind(postModels.get(position));
    }

    @Override
    public int getItemCount() {
        return postModels.size();
    }

    public void swapData(List<ArticleModel> postModels) {
        this.postModels.clear();
        this.postModels.addAll(postModels);
        notifyDataSetChanged();
    }

    public void appendList(List<ArticleModel> articleModelList) {
        int i = postModels.size();
        postModels.addAll(articleModelList);
        notifyItemRangeInserted(i, articleModelList.size());
    }

    public void setMoreListener(OnBottomReachedListener onBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_image)
        ImageView imageView;
        @BindView(R.id.tv_title)
        TextView titleTextView;
        @BindView(R.id.tv_source)
        TextView authorTextView;
        @BindView(R.id.tv_date)
        TextView dateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ArticleModel articleModel) {
            titleTextView.setText(articleModel.getTitle());
            authorTextView.setText(articleModel.getAuthor());
            dateTextView.setText(articleModel.getPublishedAt());
            GlideApp.with(itemView.getContext())
                    .load(articleModel.getUrlToImage())
                    .placeholder(R.drawable.placeholder)
                    .centerCrop().into(imageView);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), PostActivity.class);
                intent.putExtra("title", articleModel.getTitle());
                intent.putExtra("author", articleModel.getAuthor());
                intent.putExtra("image", articleModel.getUrlToImage());
                intent.putExtra("date", articleModel.getPublishedAt());
                intent.putExtra("content", articleModel.getDescription());
                intent.putExtra("url", articleModel.getUrl());
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) itemView.getContext(), imageView, "transition_image");
                itemView.getContext().startActivity(intent, options.toBundle());
            });
        }
    }
}
