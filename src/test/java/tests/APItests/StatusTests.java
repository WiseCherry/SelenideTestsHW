package tests.APItests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class StatusTests {

    @BeforeAll
    static void beforeAll () {
        Configuration.baseUrl = "https://selenoid.autotests.cloud/";
        RestAssured.registerParser("text/plain", Parser.TEXT);
    }

    @Test
    void checkTotal20() {
        get("https://selenoid.autotests.cloud/status/")
                .then()
                .body("total", is(5));

    }

    @Test
    void checkTotalWithLogs() {
        get("https://selenoid.autotests.cloud/status/")
                .then()
                .log().all()
                .contentType("text/plain")
                .statusCode(200)
                .body(containsString("total: 5"));

    }
}
