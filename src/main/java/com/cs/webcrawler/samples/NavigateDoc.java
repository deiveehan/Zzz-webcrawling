package com.cs.webcrawler.samples;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NavigateDoc {
    public static void main(String[] args) throws IOException {
        String url1 = "http://example.com";
        Document doc = Jsoup.connect(url1).get();
        Element element = doc.getElementById("content");
        Elements links = doc.getElementsByTag("a");
        for (Element link: links) {
            String linkHref = link.attr("href");
            String linkText = link.text();

            System.out.println(linkText + ":" + linkHref);
            System.out.println(link.outerHtml());
        }
    }
}
