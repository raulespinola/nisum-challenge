package org.nisum.challenge.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nisum.challenge.mapper.UserMapper;
import org.nisum.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    private static final String USER_ENDPOINT = "/user";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;

    @Test
    void create_user_response_201() {
        String request = "{\n" +
                "  \"email\": \"ra@gmail.com\",\n" +
                "  \"name\": \"Raul\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"phones\": [\n" +
                "    {\n" +
                "      \"cityCode\": \"string\",\n" +
                "      \"countryCode\": \"string\",\n" +
                "      \"number\": \"string\"\n" +
                "    },\n" +
                "{\n" +
                "      \"cityCode\": \"011\",\n" +
                "      \"countryCode\": \"2222\",\n" +
                "      \"number\": \"33333\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(request)
                .post(USER_ENDPOINT)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", equalTo("Raul"))
                .body("email", equalTo("ra@gmail.com"));
    }

    @Test
    void create_user_response_error_bad_email_format_400() {
        String request = "{\n" +
                "  \"email\": \"ra\",\n" +
                "  \"name\": \"Raul\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"phones\": [\n" +
                "    {\n" +
                "      \"cityCode\": \"string\",\n" +
                "      \"countryCode\": \"string\",\n" +
                "      \"number\": \"string\"\n" +
                "    },\n" +
                "{\n" +
                "      \"cityCode\": \"011\",\n" +
                "      \"countryCode\": \"2222\",\n" +
                "      \"number\": \"33333\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(request)
                .post(USER_ENDPOINT)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}