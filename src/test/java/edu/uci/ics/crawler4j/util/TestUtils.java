package edu.uci.ics.crawler4j.util;

import org.apache.tika.io.IOUtils;

import java.io.IOException;

public class TestUtils {
    public static String readFile(String path) throws IOException {
        return IOUtils.toString(TestUtils.class.getResourceAsStream(path));
    }
}
