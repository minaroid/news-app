package com.mina.george.newsfeed.store.models.article;

public class ArticleModel {

    private String author;
    private String title;
    private String url;
    private String description;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public ArticleModel(String author, String title,
                        String url, String description,
                        String urlToImage, String publishedAt, String content) {

        this.author = author;
        this.title = title;
        this.url = url;
        this.description = description;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

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
