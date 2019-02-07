package com.mina.george.newsfeed.store.models.article;

import com.google.gson.annotations.SerializedName;

public class ArticleResponse {

    @SerializedName("author")
    private String author;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
    @SerializedName("description")
    private String description;
    @SerializedName("urlToImage")
    private String urlToImage;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("content")
    private String content;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }
}
