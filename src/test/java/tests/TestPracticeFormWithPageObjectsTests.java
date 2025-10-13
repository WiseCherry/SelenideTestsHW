package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import pages.RegistrationPage;

import java.util.Locale;

import static pages.components.TestData.*;


@DisplayName("Келлекция тестов")
public class TestPracticeFormWithPageObjectsTests {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker(new Locale("ru"));


    @BeforeEach
    void beforeEach() {
//        firstName = "John";
//        lastName = "Shepard";
//        email = "normandy@gmail.com";
//        //gender = "";
//        phoneNumber = "9271117711";
//        birthMonth = "July";
//        birthYear = "1985";
//        //birthDay = "";
//        subject = "Economics";
//        hobbie = "Sports";
//        testFile = "1.png";
//        currentAddress = "Malibu 42\nBaker St. 66";
//        state = "Haryana";
//        city = "Panipat";


    }

    @Test
    @DisplayName("Заполнение всех данных на форме")
    void fillForm() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String phoneNumber = faker.phoneNumber().phoneNumber();
//        String birthMonth = faker.date().;
//        String birthYear = faker.date().;
//        String subject = faker.phoneNumber();
//        String hobbie = faker.phoneNumber();
        String currentAddress = faker.address().fullAddress();
//        String state = faker.phoneNumber();
//        String city = faker.phoneNumber();

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender() //male
                .setUserNumber(phoneNumber)
                .setBirthDate(birthMonth, birthYear)
                .setSubject(subject)
                .setHobbie(hobbie)
                .uploadFile(testFile)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .pressSubmit()
                .formAppearance()
                .formAsserts();
    }

    @AfterAll
    static void afterAll() {
//        sleep(3000);
    }
}
