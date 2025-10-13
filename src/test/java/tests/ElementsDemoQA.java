package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.attributes;
import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Заполнение форм 'Elements'")
public class ElementsDemoQA {


    @BeforeEach
    void beforeEachElementsCondition() {
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.pageLoadStrategy = "eager";
    }

    @DisplayName("Заполнение формы 'Text Box'")
    @Test
    void textBoxTest() {
        open("text-box/");

        //Test
        $(".text-center").shouldHave(text("Text Box")).click();
        $("#userName").setValue("John Shepard").click();
        $("#userEmail").setValue("normandy@gmail.com").click();
        $("#currentAddress").setValue("Baker st.56, New York").click();
        $("#permanentAddress").setValue("Founder st.22, Washington").click();
        $("#submit").click();

        //Asserts
        $("#output").shouldHave(text("Name:John Shepard\n" +
                "Email:normandy@gmail.com\n" +
                "Current Address :Baker st.56, New York\n" +
                "Permananet Address :Founder st.22, Washington")).click();
    }

    @Test
    @DisplayName("Чек-боксы внутри 'Home'")
    void checkBoxTest() {
        open("checkbox/");

        //Test
        $(".text-center").shouldHave(text("Check Box")).click();
        $("[title=\"Expand all\"]").click(); //развернуть
        //$("[title=\"Collapse all\"]").click(); //свернуть

        //$(byText("Home")).click();
        $(byText("React")).click();


        sleep(5000);
    }

    @Test
    @DisplayName("Чек-боксы внутри 'Radio button'")
    void radioButtonTest() {
        open("radio-button/");

        //Test
        $(".text-center").shouldHave(text("Radio Button")).click();
        //$("label[for='impressiveRadio']").click();
        //$("label[for='yesRadio']").click();
        //$$(".custom-control-label").findBy(text("Yes")).click();
        //$$(".custom-control-label").findBy(text("Impressive")).click();
        //$$(".custom-control-label").findBy(text("No")).click();
        sleep(5000);
    }

    @Test
    @DisplayName("Тесты внутри 'Web Tables'")
    void webTablesTest() {
        open("webtables/");


        sleep(5000);
    }

    @Test
    @DisplayName("Тесты внутри 'Buttons'")
    void buttonsTest() {
        open("buttons/");

        $("#doubleClickBtn").doubleClick();
        $("#doubleClickMessage").shouldHave(text("You have done a double click"));
        $("#rightClickBtn").contextClick();
        $("#rightClickMessage").shouldHave(text("You have done a right click"));
        $(byText("Click Me")).click();
        $("#dynamicClickMessage").shouldHave(text("You have done a dynamic click"));


        sleep(5000);
    }

    @Test
    @DisplayName("Тесты внутри 'Links'")
    void linkTest() {
        open("links/");
        $("#simpleLink").click();
        Selenide.switchTo().window(0);
        $("#simpleLink").click();
        Selenide.switchTo().window(0);
        $("#dynamicLink").click();
        Selenide.switchTo().window(0);
        $("#created").click();
        
        $("#no-content").click();

        $("#moved").click();

        $("#bad-request").click();

        $("#unauthorized").click();

        $("#forbidden").click();

        $("#invalid-url").click();


        sleep(5000);
    }
}
