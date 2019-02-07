package com.mina.george.newsfeed;

import android.app.Application;
import android.content.Context;

import com.mina.george.newsfeed.di.application.AppComponent;
import com.mina.george.newsfeed.di.application.AppModule;
import com.mina.george.newsfeed.di.application.DaggerAppComponent;

public class NewsApplication extends Application {

    private final AppComponent appComponent = createAppComponent();

    public static AppComponent getComponent(Context context) {
        return getApp(context).appComponent;
    }

    //This is a hack to get a non-static field from a static method (i.e. appComponent)
    private static NewsApplication getApp(Context context) {
        return (NewsApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
