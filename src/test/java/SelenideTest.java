import com.codeborne.selenide.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.devtools.v136.page.model.Screenshot;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class SelenideTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920х1080";
        Configuration.baseUrl = "https://plait-dev-k8s.sovcombank.group";
    }

    @Test
    void TestPlait(){
        // Открыть страницу теста Plait.ru
        Selenide.open("/lk/login");
        // Проверка: страница открылась - поле "Номер телефона"
        $("[name=phone]").shouldBe(visible).shouldHave(Condition.attribute("placeholder","Номер телефона"));
        // Ввести номер, нажать Enter
        $("[name=phone]").setValue("9270497717").pressEnter();
        // Проверка: страница ввода кода открылась - есть надпись "Введите код"
        $("[name=code]").shouldHave(Condition.attribute("placeholder","Код из СМС"));
        // Ввести код, нажать Enter
        $("[name=code]").setValue("111111").pressEnter();
    }

    @AfterAll
    static void afterAll() {
        Selenide.sleep(5000);
        screenshot("screen1");
    }
}