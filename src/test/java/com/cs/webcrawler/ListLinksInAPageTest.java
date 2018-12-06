package com.cs.webcrawler;

import com.cs.webcrawler.samples.ListLinksInAPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ListLinksInAPageTest {

    @Autowired
    ListLinksInAPage listLinksInAPage;

    @Test
    public void testLinksInAPage() throws IOException {
        listLinksInAPage.getLinks("http://example.com");
    }
}
