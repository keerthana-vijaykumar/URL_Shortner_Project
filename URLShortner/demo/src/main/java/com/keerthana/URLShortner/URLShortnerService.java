package com.keerthana.URLShortner;
import com.keerthana.URLShortner.services.ICSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class URLShortnerService implements IURLShortnerService, ApplicationRunner {

    List<URL> urlList = new ArrayList<>();

    @Autowired
    private ICSVService csvService;
    private static final String BASE_URL = "http://localhost:8080/";

    //  taking input as long url -> shorturl
    @Override
    public String shortenURL(String longURL, LocalDate expiryDate) throws Exception {
        // generate the new short url
        String shortUrl = generateShortURL();
        URL url = new URL(longURL, shortUrl, expiryDate);
        // add it to list
        urlList.add(url);
        // write the list to csv
        csvService.writeCsv(urlList);
        return BASE_URL + shortUrl;
    }


    private String generateShortURL() {
        final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final int SHORT_URL_LENGTH = 4; // required short url length
        StringBuilder shortURL = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            char randomChar = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            shortURL.append(randomChar);
        }
        return shortURL.toString();
    }

    @Override
    public String updateShortURL(String shortURL, String longURL) throws Exception {
        Optional<URL> result = urlList.stream().filter(urls -> Objects.equals(
                urls.getShortURL(),
                shortURL)
        ).findFirst();

        if (result.isPresent()) {
            URL urlToUpdate = result.get();
            urlToUpdate.setDestinationURL(longURL);
            csvService.writeCsv(urlList);
            return "New long URL updated successfully";
        } else {
            throw new Exception("Short URL does not exist");
        }
    }

    @Override
    public String redirectToLongUrl(String shortURL) throws Exception {
        Optional<URL> result = urlList.stream().filter(urls -> Objects.equals(
                urls.getShortURL(),
                shortURL)
        ).findFirst();

        if (result.isPresent()) {
            LocalDate expiryDate = result.get().getExpiryDate();
            LocalDate currentDate = LocalDate.now();

            if (expiryDate == null || !currentDate.isAfter(expiryDate)) {
                return result.get().getDestinationURL();
            } else {
                throw new Exception("URL Expired");
            }
        } else {
            throw new Exception("URL does not exists");
        }
    }

    @Override
    public String updateExpiry(String shortURL,long numberOfExtentionDays) throws Exception {
        Optional<URL> result = urlList.stream().filter(urls -> Objects.equals(
                urls.getShortURL(),
                shortURL)
        ).findFirst();

        if (result.isPresent()) {
            URL urlToUpdate = result.get();
            LocalDate presentExpiryDate = urlToUpdate.getExpiryDate();
            LocalDate newExtendedDate = presentExpiryDate.plusDays(numberOfExtentionDays);
            // Update the expiry date of the found URL
            urlToUpdate.setExpiryDate(newExtendedDate);
            csvService.writeCsv(urlList);
            return "Updated Expiry Date";
        } else {
            throw new Exception("Short URL does not exist");
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String[]> csvData = csvService.readCsv();
        for (int i = 0; i < csvData.size(); i++) {
            String[] row = csvData.get(i);

            if (row.length >= 3) {
                String longURL = row[0];
                String shortURL = row[1];
                String localDate = row[2];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                // Parse the string to a LocalDate using the formatter
                LocalDate expiryDate = LocalDate.parse(localDate, formatter);

                URL url = new URL(longURL, shortURL, expiryDate);
                urlList.add(url);
            }
        }

    }
}