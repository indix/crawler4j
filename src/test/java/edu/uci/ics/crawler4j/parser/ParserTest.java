package edu.uci.ics.crawler4j.parser;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;
import edu.uci.ics.crawler4j.util.TestUtils;
import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.Is.is;

public class ParserTest {
    @Test
    public void shouldParseMetaRefreshURL() throws IOException {
        String htmlFile = TestUtils.readFile("/meta-refresh.html");
        String url = "http://foo.bar/";
        Page page = new Page(urlFrom(url));
        page.setContentData(htmlFile.getBytes());
        page.setContentType("text/html");

        Parser parser = new Parser(new CrawlConfig());
        parser.parse(page, url);

        assertTrue(page.hasMetaRefresh());
    }

    private WebURL urlFrom(String url) {
        WebURL webUrl = new WebURL();
        webUrl.setURL(url);
        return webUrl;
    }
}