package sample;

import org.springframework.boot.test.web.client.TestRestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestHelper {
    private final TestRestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public TestHelper() {
        this.restTemplate = new TestRestTemplate();
        this.objectMapper = new ObjectMapper();
    }

}
