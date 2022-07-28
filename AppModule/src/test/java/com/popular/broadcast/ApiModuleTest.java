package com.popular.broadcast;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class ApiModuleTest {

    @Test
    public void testAvailability() throws Exception {

        URLConnection connection = new URL("https://run.mocky.io/v3/82c1c182-e6e8-4556-94c9-77e2523a2cc7").openConnection();
        InputStream response = connection.getInputStream();

        StringBuffer buffer = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, Charset.defaultCharset()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                buffer.append(line);
            }
        }

        assert buffer.length() > 0;
    }
}