package com.cs.webcrawler.samples;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DomCrawler {
    public static void main(String[] args) {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);

        System.out.println(doc.html());
        System.out.println(doc.title());

        System.out.println("----------------------");

        String html2 = "<div id = 'sadiv'><p>Lorem ipsum.</p>";
        Document doc2 = Jsoup.parseBodyFragment(html);
        Element body2 = doc2.body();

        System.out.println(body2);
    }
}
