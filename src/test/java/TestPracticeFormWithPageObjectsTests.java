import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestPracticeFormWithPageObjectsTests {

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void beforeAll() {
    }

    @Test
    void fillForm() {
        registrationPage.openPage()
                .setFirstName("John")
                .setLastName("Shepard")
                .setEmail("normandy@gmail.com")
                .setGender() //male
                .setUserNumber("9271117711")
                .setBirthDate("July", "1985")
                .setSubject("Economics")
                .setHobbie("Sports")
                .uploadFile("1.png")
                .setState("Haryana")
                .setCity("Panipat")
                .pressSubmit()
                .formAppearance()
                .formAsserts();
    }

    @AfterAll
    static void afterAll() {
        //sleep(3000);
    }
}
