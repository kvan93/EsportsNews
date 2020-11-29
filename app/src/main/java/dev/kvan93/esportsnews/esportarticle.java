package dev.kvan93.esportsnews;

import java.util.UUID;

public class esportarticle {
    private String guidId;
    private String title;
    private String content;
    private String feedlabel;
    private String articleUrl;

esportarticle(String guidId, String title, String content, String feedlabel,String articleUrl){
        this.guidId = guidId;
        this.content = content;
        this.feedlabel = feedlabel;
        this.title = title;
        this.articleUrl = articleUrl;
    }

    public String getGuidId() {return guidId;}

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getFeedlabel() {
        return feedlabel;
    }

    public String getArticleUrl() {
        return articleUrl;
    }
}
