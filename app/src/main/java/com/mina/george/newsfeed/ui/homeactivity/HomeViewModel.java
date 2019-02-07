package com.mina.george.newsfeed.ui.homeactivity;

import com.mina.george.newsfeed.store.models.article.ArticleModel;
import com.mina.george.newsfeed.store.repositry.PostsRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final PostsRepo postsRepo;
    private LiveData<List<ArticleModel>> articlesLiveData;
    private LiveData<String> errorLiveData;
    private int page = 1;

    public HomeViewModel(PostsRepo postsRepo) {
        this.postsRepo = postsRepo;
        this.articlesLiveData = postsRepo.getArticlesLiveData();
        this.errorLiveData = postsRepo.getErrorLiveData();
    }

    void fetchNews() {
        postsRepo.fetchNews("the-next-web", page);
    }

    void moreNews() {
        page += 1;
        postsRepo.fetchNews("the-next-web", page);
    }

    public LiveData<List<ArticleModel>> getArticlesLiveData() {
        return articlesLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    @Override
    protected void onCleared() {
        postsRepo.onCleared();
        super.onCleared();
    }
}
