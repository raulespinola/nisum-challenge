package integration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.nisum.challenge.NisumApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(classes = NisumApplication.class, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestAssuredConfigIntegracion {
    @LocalServerPort
    private int port;

    @BeforeEach
    public void configureRestAssured() {
        RestAssured.port = port;
    }

    @AfterEach
    public void end() {
        RestAssured.reset();
    }

}
