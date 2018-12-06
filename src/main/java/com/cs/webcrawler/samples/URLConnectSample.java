package com.cs.webcrawler.samples;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class URLConnectSample {
    public static void main(String[] args) throws IOException {
        String url1 = "http://example.com";
        Document doc = Jsoup.connect(url1).get();
        System.out.println("Title page: " + doc.title());
        System.out.println(doc.html());


    }
}
