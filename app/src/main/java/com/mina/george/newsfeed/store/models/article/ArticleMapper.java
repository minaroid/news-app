package com.mina.george.newsfeed.store.models.article;

import com.mina.george.newsfeed.di.scope.ApplicationScope;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@ApplicationScope
public class ArticleMapper {

    private SimpleDateFormat dateFormatOld = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateFormatNew = new SimpleDateFormat("MMM dd, yyyy");

    @Inject
    ArticleMapper() {
    }

    public ArticleModel toModel(ArticleResponse response) {
        if (response == null) return null;
        String date = response.getPublishedAt();
        try {
            date = dateFormatNew.format(dateFormatOld.parse(response.getPublishedAt().replace("T", " ")
                    .replace("Z", "")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ArticleModel(response.getAuthor(), response.getTitle(),
                response.getUrl(), response.getDescription(), response.getUrlToImage(),
                date, response.getContent());
    }

    public List<ArticleModel> toModels(List<ArticleResponse> responses) {
        if (responses == null) return null;
        List<ArticleModel> models = new ArrayList<>();
        for (ArticleResponse response : responses) {
            models.add(toModel(response));
        }
        return models;
    }
}
