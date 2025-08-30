import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestPracticeForm {
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillForm() {
        open("automation-practice-form/");
        $("#firstName").setValue("John").click();
        $("#lastName").setValue("Shepard").click();
        $("#userEmail").setValue("normandy@gmail.com").click();
        $("#gender-radio-1").parent().click();
        $("#userNumber").setValue("9271117711").click();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1985");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Economics").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("1.png");
        $("#currentAddress").setValue("Malibu 42\nBaker St. 66");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Panipat")).click();
        $("#submit").click();

        //asserts
        $(".modal-content").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("John Shepard"),
                text("normandy@gmail.com"), text("Male"),
                text("9271117711"), text("30 July,1985"), text("Economics"),
                text("Sports"), text("1.png"), text("Malibu 42 Baker St. 66"),
                text("Haryana Panipat"));

    }

    @AfterAll
    static void afterAll() {
        //sleep(3000);
    }
}
