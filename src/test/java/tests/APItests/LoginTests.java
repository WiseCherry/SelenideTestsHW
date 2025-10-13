package tests.APItests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class LoginTests {

    String authData = "";
    String otpData = "phone: \"9270497718\", recaptcha: \"test\"";

    @DisplayName("Тест логина")
    @Test
    void successfulLoginTest() {
        given()
                .body(authData)
                .contentType(JSON)

                .when()
                .log().uri()
                .get("https://plait.ru/api/features/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("description", is("Страница Логина"));

    }

    @DisplayName("Тест получения сессии")
    @Test
    void sessionLoginTest() {
        given()
                .body(otpData)
                .contentType(JSON)

                .when()
                .log().uri()
                .post("https://plait.ru/api/session")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is(60));

    }

    @DisplayName("Тест ошибки получения сессии")
    @Test
    void sessionErrorTest() {
        given()
               // .body(otpData)
                .contentType(JSON)

                .when()
                .log().uri()
                .post("https://plait.ru/api/session")

                .then()
                .log().status()
                .log().body()
                .statusCode(403);
                //.body("token", is(60));

    }

    @DisplayName("Тест otp")
    @Test
    void otpLoginTest() {
        given()
                .body(otpData)
                .contentType(JSON)

                .when()
                .log().uri()
                .get("https://plait.ru/api/otp")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("countdown", is(60));

    }
}
