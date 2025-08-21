import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        Selenide.$("[name=phone]").shouldBe(Condition.visible).shouldHave(Condition.attribute("placeholder","Номер телефона"));
        // Ввести номер, нажать Enter
        Selenide.$("[name=phone]").setValue("9270497717").pressEnter();
        // Проверка: страница ввода кода открылась - есть надпись "Введите код"
        Selenide.$("[name=code]").shouldHave(Condition.attribute("placeholder","Код из СМС"));
        // Ввести код, нажать Enter
        Selenide.$("[name=code]").setValue("111111").pressEnter();
    }

    @AfterAll
    static void afterAll() {
        Selenide.sleep(5000);
    }
}