package com.keerthana.URLShortner;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class URLController {

    @Autowired
    private IURLShortnerService urlShortnerService;

    @RequestMapping(method = RequestMethod.POST, path = "/longUrl", produces = "application/json")
    public String createShortURL(@RequestBody URL body) throws Exception {
        try{
            return urlShortnerService.shortenURL(body.getDestinationURL(),body.getExpiryDate());
        }
        catch(Exception e){
            System.out.println(e.toString());
            return "Could not create";
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{shortUrl}")
    public String getLongUrl(HttpServletResponse response, @PathVariable String shortUrl) throws Exception {
        try {
            String longURL = urlShortnerService.redirectToLongUrl(shortUrl);
            response.sendRedirect(longURL);
        } catch (Exception e) {
            System.out.println(e.toString());
            return "URL has expired";
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/updateUrl")
    public String updateShortUrl(@RequestBody URL body) throws Exception {
        try{
        return urlShortnerService.updateShortURL(body.getShortURL(),body.getDestinationURL());
        }
        catch (Exception e){
            System.out.println(e.toString());
            return "Error while updating url";
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/updateExpiry")
    public String updateExpiryDate(@RequestBody URL body) throws Exception {
        try{
        return urlShortnerService.updateExpiry(body.getShortURL(),body.getNumberOfExtentionDays());
        }
        catch (Exception e){
            System.out.println(e.toString());
            return "Error while updating expiry";
        }
    }

}
