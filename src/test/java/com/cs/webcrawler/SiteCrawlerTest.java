package com.cs.webcrawler;

import com.cs.webcrawler.samples.SiteCrawler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SiteCrawlerTest {

    @Autowired
    SiteCrawler siteCrawler;

    @Test
    public void testGetLinks() {

    }
}
