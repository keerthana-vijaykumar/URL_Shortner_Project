package com.keerthana.URLShortner;

import java.time.LocalDate;

public interface IURLShortnerService {

    //public String shortenURL(URL url) throws Exception;
    public String shortenURL(String longURL, LocalDate expiryDate) throws Exception;

    public String updateShortURL(String shortURL, String longURL) throws Exception;
    public String redirectToLongUrl(String shortURL) throws Exception;

    public String updateExpiry(String shortURL,long numberOfExtentionDays) throws Exception;



}
