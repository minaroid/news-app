package com.mina.george.newsfeed.di.activity;

import android.content.Context;

import com.mina.george.newsfeed.di.qualifier.ForActivity;
import com.mina.george.newsfeed.di.scope.ActivityScope;
import com.mina.george.newsfeed.ui.base.BaseActivity;

import androidx.fragment.app.FragmentManager;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * This class is responsible for providing the requested objects to {@link ActivityScope} annotated classes
 */

@Module
public class
ActivityModule {

    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    BaseActivity provideActivity() {
        return activity;
    }

    @ActivityScope
    @Provides
    FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @ActivityScope
    @Provides
    @ForActivity
    Context provideActivityContext() {
        return activity;
    }

    @ActivityScope
    @ForActivity
    @Provides
    CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }

}