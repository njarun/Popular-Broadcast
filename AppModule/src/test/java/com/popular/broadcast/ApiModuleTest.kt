package com.popular.broadcast

import org.junit.Assert
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.Charset

class ApiModuleTest {

    @Test
    fun testAvailability() {

        var responseValid = false

        try {

            val connection = URL("https://run.mocky.io/v3/82c1c182-e6e8-4556-94c9-77e2523a2cc7").openConnection()
            val response = connection.getInputStream()
            val buffer = StringBuffer()
            BufferedReader(InputStreamReader(response, Charset.defaultCharset())).use { reader ->

                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    buffer.append(line)
                }
            }

            responseValid = buffer.isNotEmpty()
        }
        catch (ignored: Exception) {

        }

        Assert.assertEquals(true, responseValid)
    }
}