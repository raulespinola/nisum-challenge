package org.nisum.challenge.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nisum.challenge.config.JwtAuthenticationEntryPoint;
import org.nisum.challenge.config.JwtTokenUtil;
import org.nisum.challenge.controller.dto.UserCreateResponseDto;
import org.nisum.challenge.core.model.UserCreationModel;
import org.nisum.challenge.mapper.UserMapper;
import org.nisum.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
class UserControllerTest {

    private static final String USER_ENDPOINT_REGISTER = "/register";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;



    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }


    @Test
    void create_user_response_201() {
        String request = "{\n" +
                "  \"email\": \"string@gma.com\",\n" +
                "  \"username\": \"string\",\n" +
                "  \"password\": \"strinG*123\",\n" +
                "  \"phones\": [\n" +
                "    {\n" +
                "      \"cityCode\": \"string\",\n" +
                "      \"countryCode\": \"string\",\n" +
                "      \"number\": \"string\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        UserCreateResponseDto  responseDTO = UserCreateResponseDto.builder()
                .username("Raul")
                .build();

        when(userMapper.modelToDto((UserCreationModel) any())).thenReturn(responseDTO);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(request)
                .accept(ContentType.JSON)
                .when()
                .post(USER_ENDPOINT_REGISTER)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .body("username", equalTo("Raul"));
    }


    @Test
    void create_user_response_error_bad_email_format_400() {
        String request = "{\n" +
                "  \"email\": \"ra\",\n" +
                "  \"username\": \"Raul\",\n" +
                "  \"password\": \"*78945Password\",\n" +
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

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(request)
                .accept(ContentType.JSON)
                .when()
                .post(USER_ENDPOINT_REGISTER)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void create_user_response_error_bad_password_restriction_400() {
        String request = "{\n" +
                "  \"email\": \"ra\",\n" +
                "  \"username\": \"Raul\",\n" +
                "  \"password\": \"Password\",\n" +
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

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(request)
                .accept(ContentType.JSON)
                .when()
                .post(USER_ENDPOINT_REGISTER)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }


}