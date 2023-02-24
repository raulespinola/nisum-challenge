package integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

class NisumIntegrationTest extends RestAssuredConfigIntegracion{

    private static final String USER_ENDPOINT_REGISTER = "/register";



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


        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(request)
                .accept(ContentType.JSON)
                .when()
                .post(USER_ENDPOINT_REGISTER)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .body("username", equalTo("string"));
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

        RestAssured.given()
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

        RestAssured.given()
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
