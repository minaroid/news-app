package com.mina.george.newsfeed.store.models.article;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesResponseDTO {

    @SerializedName("articles")
    List<ArticleResponse> articleResponses;

    public List<ArticleResponse> getArticleResponses() {
        return articleResponses;
    }
}
