package com.mina.george.newsfeed.di.application;

import android.app.Application;
import android.content.Context;

import com.mina.george.newsfeed.di.qualifier.ForApplication;
import com.mina.george.newsfeed.di.scope.ApplicationScope;
import com.mina.george.newsfeed.utils.ResourcesUtil;

import dagger.Module;
import dagger.Provides;

/**
 * This class is responsible for providing the requested objects to {@link ApplicationScope} annotated classes
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @ApplicationScope
    @Provides
    Application providesApplication() {
        return application;
    }

    @ApplicationScope
    @Provides
    @ForApplication
    Context providesApplicationContext() {
        return application;
    }

    @ApplicationScope
    @Provides
    ResourcesUtil providesResourcesUtil() {
        return new ResourcesUtil(application);
    }
}