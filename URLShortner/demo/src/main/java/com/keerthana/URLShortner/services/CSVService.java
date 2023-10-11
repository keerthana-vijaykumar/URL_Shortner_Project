package com.keerthana.URLShortner.services;

import com.keerthana.URLShortner.URL;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

@Component
public class CSVService implements ICSVService{

    String csvFileName = "urls.csv";
    @Override
    public List<String[]> readCsv() throws Exception {
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFileName))) {
            List<String[]> records = csvReader.readAll();
            return records;
        }
    }

    @Override
    public void writeCsv(List<URL> urlList) throws Exception {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFileName))) {
            for (URL urls : urlList){
                csvWriter.writeNext(new String[]{urls.getDestinationURL(),
                urls.getShortURL(),
                urls.getExpiryDate().toString()});
                };
            }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
