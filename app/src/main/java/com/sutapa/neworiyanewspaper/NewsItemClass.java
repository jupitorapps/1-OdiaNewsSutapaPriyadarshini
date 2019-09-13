package com.sutapa.neworiyanewspaper;

class NewsItemClass {

    private int imageResourceId;
    private String ePaerpUrl;
    private String websiteUrl;

    NewsItemClass(int imageResourceId, String ePaerpUrl, String websiteUrl) {
        this.imageResourceId = imageResourceId;
        this.ePaerpUrl = ePaerpUrl;
        this.websiteUrl = websiteUrl;

    }

    int getImageResourceId() {
        return imageResourceId;
    }

    String getePaerpUrl() {
        return ePaerpUrl;
    }

    String getWebsiteUrl() {
        return websiteUrl;
    }

}
