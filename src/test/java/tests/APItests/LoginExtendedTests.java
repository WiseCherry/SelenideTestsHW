package tests.APItests;

import models.LoginBodyModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginExtendedTests {

    @DisplayName("Тест логина - как не надо делать")
    @Test
    void successfulLoginBadPracticeTest() {
        String authData = "";
        String otpData = "phone: \"9270497718\", recaptcha: \"test\"";

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

    @DisplayName("Тест логина - как надо делать")
    @Test
    void successfulLoginTest() {

        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("test@test.ru");
        authData.setPassword("123123");

        LoginResponseModel responce = given()
                .body(authData)
                .contentType(JSON)

                .when()
                .log().uri()
                .get("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

                assertEquals("11111", responce.getToken());

    }
}
