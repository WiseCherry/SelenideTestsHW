package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import java.security.PublicKey;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    CalendarComponent calendarComponent = new CalendarComponent();

    public static SelenideElement firstNameLocator = $("#firstName");
    public static SelenideElement lastNameLocator = $("#lastName");
    public static SelenideElement emailLocator = $("#userEmail");
    public static SelenideElement genderLocatorMale = $("#gender-radio-1"); //male
    public static SelenideElement userNumberLocator = $("#userNumber");
    public static SelenideElement dateBirthInputLocator = $("#dateOfBirthInput");
    public static SelenideElement monthBirthLocator = $(".react-datepicker__month-select");
    public static SelenideElement yearBirthLocator = $(".react-datepicker__year-select");
    public static SelenideElement dayBirthLocator = $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)");
 //   public static SelenideElement calendarInputLocator = $("#dateOfBirthInput");
    public static SelenideElement subjectLocator = $("#subjectsInput");
    public static SelenideElement hobbieLocator = $("#hobbiesWrapper");
    public static SelenideElement fileUploaderLocator = $("#uploadPicture");
    public static SelenideElement stateLocator = $("#state");
    public static SelenideElement stateCityLocator = $("#stateCity-wrapper");
    public static SelenideElement cityLocator = $("#city");
    public static SelenideElement submitButtonLocator = $("#submit");




    public RegistrationPage openPage() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.pageLoadStrategy = "eager";
        open("automation-practice-form/");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameLocator.setValue(value).click();

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameLocator.setValue(value).click();

        return this;
    }

    public RegistrationPage setEmail(String value) {
      emailLocator.setValue(value).click();

        return this;
    }

    public RegistrationPage setGender() {
        genderLocatorMale.parent().click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberLocator.setValue(value).click();

        return this;
    }

    public RegistrationPage setBirthDate (String month, String year) {
//        calendarInputLocator.click();
//        calendarComponent.setDate(day, month, year);
       dateBirthInputLocator.click();
       monthBirthLocator.selectOption(month);
       yearBirthLocator.selectOption(year);
       dayBirthLocator.click();

    return this;
    }

    public RegistrationPage setSubject (String value) {
        subjectLocator.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage setHobbie (String value) {
        hobbieLocator.$(byText(value)).click();

        return this;
    }

    public RegistrationPage uploadFile (String value) {
        fileUploaderLocator.uploadFromClasspath(value);

        return this;
    }

    public RegistrationPage setState (String value) {
        stateLocator.click();
        stateCityLocator.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setCity (String value) {
        cityLocator.click();
        stateCityLocator.$(byText(value)).click();

        return this;
    }

    public RegistrationPage pressSubmit () {
        submitButtonLocator.click();

        return this;
    }

    public RegistrationPage formAppearance () {
        $(".modal-content").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        return this;
    }

    public RegistrationPage formAsserts () {
        $(".table-responsive").shouldHave(text("John Shepard"),
                text("normandy@gmail.com"), text("Male"),
                text("9271117711"), text("30 July,1985"), text("Economics"),
                text("Sports"), text("1.png"), text("Malibu 42 Baker St. 66"),
                text("Haryana Panipat"));

        return this;
    }

}
