package com.cs.webcrawler.samples;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.net.www.content.image.png;

import java.io.IOException;

public class SelectorDoc {
    public static void main(String[] args) throws IOException {
        String url1 = "https://www.facebook.com";
        Document doc = Jsoup.connect(url1).get();
        Element element = doc.getElementById("content");
        Elements links = doc.select("a[href]");
        Elements pngs = doc.select("img[src$=.png]");

        Element masthead = doc.select("div.masthead").first();

        for (Element link: links) {
            String linkHref = link.attr("href");
            String linkText = link.text();
            String absolutelinkHref = link.attr("abs:href");

            System.out.println(linkText + ":" + linkHref);
            System.out.println("Absolute: " + linkText + ":" + absolutelinkHref);

            //System.out.println(link.outerHtml());
        }

/*
        System.out.println("--------------");
        for (Element png: pngs) {
            String linkHref = png.attr("href");
            String absolutelinkHref = png.attr("abs:href");
            String linkText = png.text();

            System.out.println(linkText + ":" + linkHref);
            System.out.println("Absolute: " + linkText + ":" + absolutelinkHref);

            //System.out.println(png.outerHtml());
        }

*/

        // tagname, #id, .class, [attribute], [attr=value], *, div#id,
    }
}
