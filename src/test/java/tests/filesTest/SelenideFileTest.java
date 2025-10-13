package tests.filesTest;

import com.codeborne.selenide.Configuration;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


@DisplayName("Тесты с файлами")
public class SelenideFileTest {

    @DisplayName("Тест на скачивание файла")
    @Test
    void downloadFileTest() throws Exception{
        Configuration.baseUrl = "https://github.com/";
        //Configuration.pageLoadStrategy = "eager";

        open("WiseCherry/SelenideTestsHW/blob/main/README.md");
        File downloaded = $(".react-blob-header-edit-and-raw-actions [href*='/README.md']")
                .download();

//        try (InputStream is = new FileInputStream(downloaded))  {
//            byte[] data = is.readAllBytes();
//            String dataString = new String(data, StandardCharsets.UTF_8);
//            Assertions.assertEquals(dataString.contains("readme"));
//        }

        String dataAsString = FileUtils.readFileToString(downloaded, StandardCharsets.UTF_8);
    }

    @DisplayName("Загрузка файла")
    @Test
    void uploadFileTest() {
        Configuration.baseUrl = "https://demoqa.com/upload-download";
        //Configuration.pageLoadStrategy = "eager";

        open("upload-download/");
        $(".uploadFile input[type='file']").uploadFromClasspath("sad_cat.jpg");
        $(".uploadedFilePath").shouldHave(text("sad_cat.jpg"));
    }
}
