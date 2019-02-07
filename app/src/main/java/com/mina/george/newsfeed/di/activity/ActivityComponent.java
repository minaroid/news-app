package com.mina.george.newsfeed.di.activity;

import com.mina.george.newsfeed.di.scope.ActivityScope;
import com.mina.george.newsfeed.ui.base.BaseActivity;
import com.mina.george.newsfeed.ui.homeactivity.HomeActivity;
import com.mina.george.newsfeed.ui.postactivity.PostActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);

    void inject(HomeActivity homeActivity);

    void inject(PostActivity postActivity);

}