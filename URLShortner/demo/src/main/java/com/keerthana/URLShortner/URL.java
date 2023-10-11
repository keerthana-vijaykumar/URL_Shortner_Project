package com.keerthana.URLShortner;
import java.time.LocalDate;

public class URL {
    private String destinationURL;
    private String shortURL;
    private long numberOfExtentionDays;
    private LocalDate expiryDate;

    public URL(String destinationURL, String shortURL, LocalDate expiryDate) {
        this.destinationURL = destinationURL;
        this.shortURL = shortURL;
        this.expiryDate = expiryDate;
    }

    public String getDestinationURL() {
        return destinationURL;
    }

    public void setDestinationURL(String destinationURL) {
        this.destinationURL = destinationURL;
    }


    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public long getNumberOfExtentionDays() {
        return numberOfExtentionDays;
    }

    public void setNumberOfExtentionDays(long numberOfExtentionDays) {
        this.numberOfExtentionDays = numberOfExtentionDays;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

//    public String getUrlString() {
//        return urlString;
//    }
//
//    public void setUrlString(String urlString) {
//        this.urlString = urlString;
//    }
}
