package com.example.spring_web_log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

class CustomLogstashEncoderTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final Logger logger = LoggerFactory.getLogger(CustomLogstashEncoderTest.class);

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testConsoleOutput() throws JsonProcessingException {
        logger.info("Hello World!");
        TypeReference<HashMap<String,String>> typeRef
                = new TypeReference<HashMap<String,String>>() {};
        String capturedOutput = outContent.toString();
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map =  mapper.readValue(capturedOutput, typeRef);


        Assertions.assertNotNull(map.get("@timestamp"));
    }
}