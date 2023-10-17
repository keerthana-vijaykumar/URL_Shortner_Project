package com.keerthana.URLShortner.services;

import com.keerthana.URLShortner.URL;

import java.util.List;

public interface ICSVService {

    public List<String[]> readCsv() throws Exception;

    public void writeCsv(List<URL> urlList) throws Exception;
}
