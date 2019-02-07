package com.mina.george.newsfeed.ui.homeactivity;

import com.mina.george.newsfeed.di.scope.ActivityScope;
import com.mina.george.newsfeed.store.repositry.PostsRepo;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

@ActivityScope
public class HomeViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final PostsRepo postsRepo;

    @Inject
    HomeViewModelFactory(PostsRepo postsRepo) {
        this.postsRepo = postsRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(postsRepo);
    }
}
