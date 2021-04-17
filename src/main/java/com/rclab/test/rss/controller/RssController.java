package com.rclab.test.rss.controller;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
@RequestMapping(value = "/rss", produces = "application/json;charset=UTF-8")
public class RssController {

    @GetMapping(value = "/reader")
    public ResponseEntity<SyndFeed> getRssReader(@RequestParam(defaultValue = "SK+SV") String searchWord) {
        SyndFeed result = null;
        String url = "https://news.google.com/rss/search?q="+ searchWord + "&hl=ko&gl=KR&ceid=KR:ko";

        try (XmlReader reader = new XmlReader(new URL(url))) {
            result = new SyndFeedInput().build(reader);
        } catch(Exception e) {
            System.out.println(e);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
