package com.cs.webcrawler.samples;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;

@Component
public class SiteCrawler {

    private static HashSet<String> siteLinks = new HashSet<String>();
    private static int crawlLevel = 2;
    private static int index = 0;
    private static String sessionId = null;

    public static void main(String[] args) throws IOException {
        SiteCrawler sc = new SiteCrawler();
        crawlSite();
        printSiteLinks(siteLinks);
        print("\n -- Total links: " + siteLinks.size());

    }

    public static void crawlSite() throws IOException {
        // Login page get links
        String facingUrl = "https://devprorq2.realquest.com";
        getLinks(facingUrl);

        // Login to the application.
        String loginURL = "";
        Connection.Response res = Jsoup.connect(loginURL).followRedirects(true)
                .data("username", "")
                .data("password", "").execute();
        Document doc = res.parse();
        sessionId = res.cookie("JSESSIONID");

        // Get home page links.
        String homePageURL = "http://devprorq2.realquest.com/servlet/workflow/main";
        index = 0;
        getLinks(homePageURL);

        String subjectSearchURL = "http://devprorq2.realquest.com/servlet/workflow/main#/search/property";
        index = 0;
        getLinks(subjectSearchURL);

        String customSearchURL = "http://devprorq2.realquest.com/jsp/rq.jsp?&client=&page=Custom_Search&tab=cs&action=switch&now=1544047422668";
        index = 0;
        getLinks(customSearchURL);

        String foreClosureSearchURL = "http://devprorq2.realquest.com/jsp/rq.jsp?&client=&page=Foreclosure_Search&tab=fc&action=switch&now=1544101522410";
        index = 0;
        getLinks(foreClosureSearchURL);

        String parcelMapClosureSearchURL = "http://devprorq2.realquest.com/jsp/rq.jsp?&client=&page=Parcel_Map_Search&tab=pm&action=switch&now=1544101522410";
        index = 0;
        getLinks(parcelMapClosureSearchURL);

        String orderVerificationServicesURL = "http://devprorq2.realquest.com/jsp/rq.jsp?&client=&page=Verify_Services&tab=vs&action=switch&now=1544101522410";
        index = 0;
        getLinks(orderVerificationServicesURL);


        String onsiteServicesURL = "http://devprorq2.realquest.com/jsp/rq.jsp?&client=&page=Onsite_Services&do=onsite_order_listpage&tab=os&action=switch&now=1544101522410";
        index = 0;
        getLinks(onsiteServicesURL);


        String docNumberSearchURL = "http://devprorq2.realquest.com/jsp/rq.jsp?&client=&page=Document_Number_Search&tab=di&action=switch&opt=onlineorder&now=1544101522410";
        index = 0;
        getLinks(docNumberSearchURL);


        String batchSearchURL = "http://devprorq2.realquest.com/jsp/rq.jsp?&client=&page=Batch_Search&show=create&action=switch&now=1544101522410";
        index = 0;
        getLinks(batchSearchURL);

        String reportOptionsURL = "http://devprorq2.realquest.com/jsp/report.jsp?&client=&action=confirm&type=getreport&recordno=-1&reportoptions=97dca957-5b3c-4c62-9ba1-493eb6628569&how=direct&1544102953397&1544102953397";
        index = 0;
        getLinks(reportOptionsURL);

        String reportOptions2URL = "http://devprorq2.realquest.com/jsp/report.jsp?&client=&action=confirm&type=getreport&recordno=-1&reportoptions=ee53637d-855e-4047-9940-5ddd488df1d9&how=direct&1544102973035&1544102973035";
        index = 0;
        getLinks(reportOptions2URL);

        String loanSafeAppraisalURL = "http://devprorq2.realquest.com/jsp/rq.jsp?&client=&page=Input&action=switch&lastaction=confirm&type=input&rpt=loansafeAppraisal&recordno=-1&reportoptions=5ede4247-f4cd-4b6a-a9bc-5fa2dcacb390&how=&how=direct&cont=&1544102997708&";
        index = 0;
        getLinks(loanSafeAppraisalURL);


        String adminURL = "http://devprorq2.realquest.com/jsp/rq.jsp?&client=&page=Admin&action=switch&p=1&sp=1&now=1544101522410";
        index = 0;
        getLinks(adminURL);

        String filesURL = "http://devprorq2.realquest.com/jsp/rq.jsp?&client=&page=MyFiles_Menu&tab=mf_menu&action=switch&now=1544101522410";
        index = 0;
        getLinks(filesURL);

        String preferencesURL = "http://devprorq2.realquest.com/jsp/rq.jsp?&client=&page=Customer_Preferences&action=switch&p=1&sp=1&now=1544101522410";
        index = 0;
        getLinks(preferencesURL);

        String updateUserURL = "http://devprorq2.realquest.com/jsp/rq.jsp?&client=&page=Update_User&tab=mf_menu&action=switch";
        index = 0;
        getLinks(updateUserURL);






    }

    public static void getLinks(String url) throws IOException {
        if(index > crawlLevel) {
            return;
        }
        index++;

        Document doc;
        System.out.println("Session id: " + sessionId);
        if(sessionId == null) {
            doc = Jsoup.connect(url).get();

        } else {
             doc = Jsoup.connect(url).cookie("JSESSIONID", sessionId).header("JSESSIONID", sessionId).get();
        }

        String title = doc.title();
        print("\n Title:" + title);

        Elements links = doc.select("a[href]");
        for (Element link : links) {
            String text = trim(link.text(), 35);
            String absoluteURL = link.attr("abs:href");
            String textOuterHtml = link.outerHtml();

            print(" * %s %s  (%s) - %s", index, absoluteURL, text, textOuterHtml );
            if(textOuterHtml.contains("window.open")) {
                if(textOuterHtml.contains("http")) {
                    siteLinks.add(textOuterHtml.substring(textOuterHtml.indexOf("http"), textOuterHtml.length()));

                } else {
                    siteLinks.add(url + textOuterHtml.substring(textOuterHtml.indexOf("window.open('") + 13, textOuterHtml.length()));
                }
            } else {
                siteLinks.add(absoluteURL);
                try {
                    getLinks(absoluteURL);

                }
                catch(IllegalArgumentException ie) {
                    System.out.println(ie.getMessage() + "URL : " + absoluteURL);
                }
            }

        }
    }


    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }


    public static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    public static void printSiteLinks(HashSet<String> siteLinks ) {
        for(String siteLink: siteLinks) {
            System.out.println(siteLink);
        }
    }
}
