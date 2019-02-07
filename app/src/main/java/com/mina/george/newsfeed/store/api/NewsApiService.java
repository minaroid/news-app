package com.mina.george.newsfeed.store.api;

import com.mina.george.newsfeed.store.models.article.ArticleResponse;
import com.mina.george.newsfeed.store.models.article.ArticlesResponseDTO;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("everything")
    Single<ArticlesResponseDTO> fetchNews(
            @Query("q") String q,
            @Query("apiKey") String apiKey,
            @Query("page") Integer page);
}