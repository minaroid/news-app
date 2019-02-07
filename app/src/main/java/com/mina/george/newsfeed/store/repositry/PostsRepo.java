package com.mina.george.newsfeed.store.repositry;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.mina.george.newsfeed.R;
import com.mina.george.newsfeed.di.qualifier.ForActivity;
import com.mina.george.newsfeed.di.scope.ActivityScope;
import com.mina.george.newsfeed.store.api.ApiUtils;
import com.mina.george.newsfeed.store.models.article.ArticleMapper;
import com.mina.george.newsfeed.store.models.article.ArticleModel;
import com.mina.george.newsfeed.store.models.error.ErrorResponse;
import com.mina.george.newsfeed.utils.ResourcesUtil;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class PostsRepo {

    private final ResourcesUtil resourcesUtil;
    private final CompositeDisposable compositeDisposable;
    private final ApiUtils apiUtils;
    private final ArticleMapper articleMapper;
    private MutableLiveData<List<ArticleModel>> articlesLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    @Inject
    PostsRepo(@ForActivity CompositeDisposable compositeDisposable,
              ResourcesUtil resourcesUtil, ApiUtils apiUtils, ArticleMapper articleMapper) {
        this.apiUtils = apiUtils;
        this.articleMapper = articleMapper;
        this.resourcesUtil = resourcesUtil;
        this.compositeDisposable = compositeDisposable;
    }

    public void fetchNews(String q, int page) {
        compositeDisposable.add(apiUtils.getNewsApiService().fetchNews(q, ApiUtils.API_KEY, page)
                .map(v -> articleMapper.toModels(v.getArticleResponses()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articlesLiveData::setValue, this::processError));
    }

    public LiveData<List<ArticleModel>> getArticlesLiveData() {
        return articlesLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    void processError(Throwable t) {
        if (t instanceof HttpException) {
            errorLiveData.setValue(getHttpErrorMessage((HttpException) t));
        } else if (t instanceof IOException) {
            errorLiveData.setValue(resourcesUtil.getString(R.string.error_network));
        } else {
            errorLiveData.setValue(resourcesUtil.getString(R.string.error_communicating_with_server));
        }
    }

    private String getHttpErrorMessage(HttpException httpException) {
        Gson gson = new Gson();
        try {
            ErrorResponse errorResponse = gson.fromJson(httpException.response().errorBody().string(), ErrorResponse.class);
            if (errorResponse != null) {
                return errorResponse.getMessage();
            } else {
                return resourcesUtil.getString(R.string.error_communicating_with_server);
            }
        } catch (Exception e) {
            return resourcesUtil.getString(R.string.error_communicating_with_server);
        }
    }

    public void onCleared() {
        compositeDisposable.dispose();
    }
}
