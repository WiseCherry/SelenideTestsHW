package Selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.CollectionCondition.exactTextsCaseSensitiveInAnyOrder;
import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThanOrEqual;
import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.textCaseSensitive;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class Snippets {
    void browserCommandExamples() {
        //https://www.youtube.com/watch?v=X1ir-4uwczw
        Selenide.open();
        Selenide.back(); //шаг назад
        Selenide.refresh(); //перезагрузка страницы
        Selenide.clearBrowserCookies(); //очистка куки
        Selenide.clearBrowserLocalStorage(); //очистка кэша
        Selenide.confirm(); //подтвердить в диалоге после клика на кнопку
        Selenide.dismiss(); //отменить в дилоге после крика на кнопку
        Selenide.closeWindow(); //закрыть активную вкладку
        Selenide.closeWebDriver(); //закрыть браузер
        Selenide.switchTo().window("The Internet"); //сменить вкладку
        Selenide.switchTo().frame("new"); //выбрать фрейм на странице
        Selenide.switchTo().defaultContent(); //вернуться из фрейма назад на гблавную

        Cookie cookie = new Cookie("foo", "bar");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie); //установки своихх куки
    }

    void selectorExamples() {
        $("div").click(); //поиск элемента по селектору
        Selenide.element("div").click(); //поиск элемента по селектору
        $("div", 2).click(); //поиск элемента по селектору с индексом (третий div)
        Selenide.$x("//h1/div").click(); //поиск элемента по xpath
        $(byXpath("//h1/div")).click(); //поиск элемента по xpath
        $(byText("full text")); //поиск по тексту
        $(withText("ull tex")); //поиск по части текста
        $(byTagAndText("div","full text")); //поиск текста по тегу
        $(withTagAndText("div","ull tex")); //поиск части текста по тегу
        $("").parent(); //поиск по родителю (вверх по дереву)
        $("").sibling(1); //поиск по родственнику (вниз по дереву, начинается с 0)
        $("").preceding(1); //поиск (вверх по дереву, начинается с 0)
        $("").closest("div"); //поиск по предку (вверх по дереву)
        $("").ancestor("div"); //поиск по предку (вверх по дереву)
        $("div:last-child"); //поиск по последнемук ребенку
        $("div").$("h1").find(byText("abc")).click(); //поиск по 1 элементу, 2 элементу и тексту
        $("[abc=x]").click(); //поиск элемента по аттрибуту
        $("#mytext").click(); //поиск элемента по id
        $(".red").click(); //поиск элемента по классу
    }

    void actionsExamples() {
        $("").click(); //клик по элементу
        $("").doubleClick(); //двойной клик по элементу
        $("").contextClick(); //правый клик
        $("").hover(); //подвести курсор, но не нажимать
        $("").setValue("text"); //записать значение в поле
        $("").append("text"); //добавляет значение в конец текста (который уже есть в поле)
        $("").clear(); //очистить поле
        $("").setValue(""); //при пустом значении работает как "очистить"
        $("div").sendKeys("c"); //нажать горячую клавишу на элементе
        Selenide.actions().sendKeys("c").perform(); //горячая клавиша на всем приложении
        Selenide.actions().sendKeys(Keys.chord(Keys.CONTROL,"f")).perform(); //вызвать комбинацию клавиш "CTRL+F"
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f")); //вызвать комбинацию клавиш "CTRL+F"
        $("").pressEnter(); //нажать Enter
        $("").pressEscape(); //нажать Esc
        $("").pressTab(); //нажать Tab
        Selenide.actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform(); //передвинуть курсор на нужное место (слева направо и сверху вниз)
        $("").selectOption(("dropdown_option")); //выбрать значение в дропдауне (контекстный список и чекбоксы) - для старых
        $("").selectRadio("radio_options"); //выбрать значение в дропдауне (контекстный список и чекбоксы) - для новых
    }

    void assertionExamples() {
        $("").shouldBe(visible); //элемент должен быть виден
        $("").shouldNotBe(visible); //элемент НЕ должен быть виден
        $("").shouldHave(text("abc")); //элемент должен содержать текст "abc"
        $("").shouldNotHave(text("abc")); //элемент НЕ должен содержать текст "abc"
        $("").should(appear); //элемент должен появиться
        $("").shouldNot(appear); //элемент НЕ должен появиться
        $("").shouldBe(visible, Duration.ofSeconds(30)); //элемент должен быть виден втечении 30 сек
    }

    void conditionExamples() {
        $("").shouldBe(visible); //элемент должен быть виден
        $("").shouldBe(hidden); //элемент должен быть скрыт
        $("").shouldHave(text("text")); //проверка на текст
        $("").shouldHave(exactText("text")); //проверка на точный текст
        $("").shouldHave(textCaseSensitive("text")); //проверка на заглавные или строчные в тексте
        $("").should(matchText("[0-9]abc$")); //проверка на формат текста
        $("").shouldHave(cssClass("red")); //содtржит ли элемент данный класс
        $("").shouldHave(cssValue("font-size","25")); //проверка размера шрифта
        $("").shouldHave(value( "25")); //проверка нужного значения
        $("").shouldHave(exactValue( "25")); //проверка нужного значения (конкретного)
        $("").shouldBe(Condition.empty); //должен быть пустой
        $("").shouldHave(attribute("disabled")); //проверка на атррибут
        $("").shouldHave(attribute("name", "example")); //проверка на атррибут с условиями
        $("").shouldHave(attributeMatching("name", "[0-9]abc$")); //проверка на атррибут с условиями (регулярка)
        $("").shouldBe(checked); //чекбокс (true)
        $("").shouldNotBe(checked); //чекбокс (false)
        $("").should(exist); //проверка наличия элемента, но не его видимости
        $("").shouldBe(disabled); //проверка на выключенный элемент
        $("").shouldBe(enabled); //проверка на включенный элемент
    }

    void collectionsExamples() {
        $$("div"); //поиск всех элементов (но само по себе ничего не делает)
        $$x("//div"); //поиск всех элементов (но само по себе ничего не делает)

        //selection
        $$("div").filterBy(text("123")).shouldHave(size(1)); //фильтруются элементы по условию
        $$("div").excludeWith(text("123")).shouldHave(size(1)); //фильтруются элементы которые НЕ подхлдят по условию
        $$("div").first().click(); //первый элемент из списка
        elements("div").first().click(); //первый элемент из списка
        $$("div").last().click(); //последний элемент из списка
        $$("div").get(1).click(); //второй элемент из списка (начинается с 0)
        $("div", 1).click(); //второй элемент из списка (начинается с 0)
        $$("div").findBy(text("123")).click(); //фильтрует и берет первый

        //assertion
        $$("").shouldHave(size(0)); //проверка на размер (0)
        $$("").shouldBe(CollectionCondition.empty); //проверка на размер (0)
        $$("").shouldHave(texts("Alfa", "Beta", "Gamma")); //проверка на кол-во текста
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma")); //проверка на кол-во конкретного текста
        $$("").shouldHave(textsInAnyOrder("Alfa", "Beta", "Gamma")); //проверка на текст в любом порядке
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Alfa", "Beta", "Gamma")); //проверка на текст в любом порядке со строгим регистром
        $$("").shouldHave(itemWithText("Gamma")); //хотя бы один элемент с этим текстом
        $$("").shouldHave(sizeGreaterThan(0)); //проверка на размер (больше 0)
        $$("").shouldHave(sizeGreaterThanOrEqual(1)); //проверка на размер (больше или равно 1)
        $$("").shouldHave(sizeLessThan(3)); //проверка на размер (меньше 3)
        $$("").shouldHave(sizeLessThanOrEqual(2)); //проверка на размер (меньше или равно 2)
    }

    void fileOperationExamples() {
        File file1 = $("a.fileLink").download(); //скачивание только для типа <a href="..."> ссылок
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); //скачивание

        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("readme.txt");
        $("upLoadButton").click(); //НЕ ЗАБЫВАТЬ ПОДТВЕРДИТЬ
    }

    void javascriptExamples() {
        executeJavaScript("alert('selenide')");
        executeJavaScript("alert(arguments[0]*arguments[1])", 6,7);
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1]", 6,7);
    }
}
