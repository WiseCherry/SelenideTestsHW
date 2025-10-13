package tests.filesTest;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.model.Glossary;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Тест PDF")
public class FilesParsingTest {

    private ClassLoader cl = FilesParsingTest.class.getClassLoader();
    private static final Gson gson = new Gson();

    @DisplayName("Тест парсинга ПДФ")
    @Test
    void pdfFileParsingTest() throws Exception {
        open("https://docs.junit.org/current/user-guide/");
        File downloaded = $("[href='junit-user-guide-5.13.4.pdf']").download();
        PDF pdf = new PDF(downloaded);
        Assertions.assertEquals("JUnit 5 User Guide", pdf.text);
    }

    @DisplayName("Тест парсинга эксель")
    @Test
    void xlsFileParsingTest() throws Exception {
        open("https://excelvba.ru/programmes/Teachers");
        File downloaded = $("[href='https://ExcelVBA.ru/sites/default/files/teachers.xls']").download();
        XLS xls = new XLS(downloaded);
        String stringCellValue = xls.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue();
        Assertions.assertEquals("1. Суммарное количество часов планируемое на штатную по всем", stringCellValue);

    }

    @DisplayName("Парсинг csv файла")
    @Test
    void csvFileParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("example.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {

            List<String[]> data = csvReader.readAll();
            Assertions.assertEquals(2, data.size());
            Assertions.assertEquals(
                    new String[]{"selenide", "lesson1"},
                    data.get(0)
            );
            Assertions.assertEquals(
                    new String[]{"junit", "lesson2"},
                    data.get(1)
            );
        }
    }

    @DisplayName("Парсинг zip файла")
    @Test
    void zipFileParsingTest() throws Exception {

    }

    @DisplayName("Парсинг json файла")
    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("glossary.json")
        )) {
            Glossary actual = gson.fromJson(reader, Glossary.class);

            Assertions.assertEquals("example glossary", actual.getTitle());
            Assertions.assertEquals(3256, actual.getId());
            Assertions.assertEquals("SGML", actual.getGlossary().getSortAs());
            Assertions.assertEquals("Standard Generalized Markup Language", actual.getGlossary().getGlossTerm());
            Assertions.assertEquals("SGML", actual.getGlossary().getAcronym());
        }
    }
}
