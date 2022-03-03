package com.example.server;

import com.example.client.ExampleFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ImportAutoConfiguration()
@ContextConfiguration(classes = {TestConfig.class})
@SpringBootTest(classes = RestTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RunFromIntModuleTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ExampleFeignClient client;

    @BeforeAll
    public void setup() {
        RestAssured.baseURI = "http://localhost:" + port;
        RestAssured.port = 443;
    }

    @Test
    void test(){
        Response response = RestAssured.given()
                .when()
                .get("hello")
                .andReturn();
        assertThat(response.getStatusCode()).isEqualTo(200);
        String s = response.getBody().prettyPrint();
        assertThat(s).isEqualTo("Hello world!");
    }

    @Test
    void test2(){
        String response = client.getHello();
        assertThat(response).isEqualTo("Hello world!");

    }
}
