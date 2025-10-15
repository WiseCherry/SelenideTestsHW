package tests.APItests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.LoginBodyLombokModel;
import models.lombok.LoginResponseLombokModel;
import models.pojo.LoginBodyModel;
import models.pojo.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
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

    @DisplayName("Тест логина - pojo")
    @Test
    void successfulLoginPojoTest() {

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

    @DisplayName("Тест логина - lombok")
    @Test
    void successfulLoginLombokTest() {

        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setEmail("test@test.ru");
        authData.setPassword("123123");

        LoginResponseLombokModel response = given()
                .log().uri()
                .log().body()
                .log().headers()
                .body(authData)
                .contentType(JSON)

                .when()
                .get("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertEquals("error", response.getToken());
    }

    @DisplayName("Тест логина - allure")
    @Test
    void successfulLoginAllureTest() {

        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setEmail("test@test.ru");
        authData.setPassword("123123");

        LoginResponseLombokModel loginResponseLombokModel = new LoginResponseLombokModel();
        loginResponseLombokModel.setError("Missing API key");

        LoginResponseLombokModel response = given()

                .filter(new AllureRestAssured())
                .log().uri()
                .log().body()
                .log().headers()
                .body(authData)
                .contentType(JSON)

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(401)
                .extract().as(LoginResponseLombokModel.class);

        assertEquals("Missing API key", response.getError());
    }

    @DisplayName("Тест логина - allure")
    @Test
    void successfulLoginWithStepsTest() {

        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setEmail("test@test.ru");
        authData.setPassword("123123");

        LoginResponseLombokModel loginResponseLombokModel = new LoginResponseLombokModel();
        loginResponseLombokModel.setError("Missing API key");

        LoginResponseLombokModel response = step("Make request", () ->
                 given()

                    .filter(new AllureRestAssured())
                    .log().uri()
                    .log().body()
                    .log().headers()
                    .body(authData)
                    .contentType(JSON)

                 .when()
                    .post("https://reqres.in/api/login")

                 .then()
                    .log().status()
                    .log().body()
                    .statusCode(401)
                    .extract().as(LoginResponseLombokModel.class));

        step("Check response", () ->
            assertEquals("Missing API key", response.getError()));
    }
}
