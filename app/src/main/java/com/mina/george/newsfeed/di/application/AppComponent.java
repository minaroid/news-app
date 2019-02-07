package com.mina.george.newsfeed.di.application;

import com.mina.george.newsfeed.NewsApplication;
import com.mina.george.newsfeed.di.activity.ActivityComponent;
import com.mina.george.newsfeed.di.activity.ActivityModule;
import com.mina.george.newsfeed.di.scope.ApplicationScope;

import dagger.Component;

/**
 * This interface is used by dagger to generate the code that defines the connection between the provider of objects
 * (i.e. {@link AppModule}), and the object which expresses a dependency.
 */

@ApplicationScope
@Component(modules = {AppModule.class})
public interface AppComponent {

    ActivityComponent plus(ActivityModule activityModule);

    void inject(NewsApplication newsApplication);
}