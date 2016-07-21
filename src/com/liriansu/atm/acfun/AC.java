package com.liriansu.atm.acfun;

public class AC {
    private final long   id;
    private final String html;
    private final String article;

    public AC(long id, String html, String article) {
        this.id = id;
        this.html = html;
        this.article = article;
    }

    public long getId() {
        return id;
    }

    public String getHtml() {
        return html;
    }

    public String getArticle() {
        return article;
    }
}
