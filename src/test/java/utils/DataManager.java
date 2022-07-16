package utils;

import org.testng.Assert;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.testng.Assert.fail;

public class DataManager {
    private Properties properties;

    /**
     * Получает список юзеров
     * @return String json со списком юзеров
     */
    public String getUsers() {
        String text = "";
        try {
            text = new String(
                    Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/data/users.json")),
                    StandardCharsets.UTF_8
            );
        } catch (Throwable e) {
            fail("Не удалось считать данные юзеров из файла\n" + e.getMessage());
        }
        return text;
    }

    /**
     * получает данные из проперти-файла
     * @param prop - свойство
     * @return String значение
     */
    public String getData(String prop) {
        properties = new Properties();
        try (FileInputStream propInpStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/data/data.properties")) {
            properties.load(new InputStreamReader(propInpStream, Charset.forName("windows-1251")));
        } catch (Throwable e) {
            System.out.println("Проблема с чтением проперти файла с данными для теста");
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
        return properties.getProperty(prop);
    }

    /**
     * возвращает путь до файла, который используется в тестах
     * @param fileName String имя файла
     * @return String путь к файлу
     */
    public String getPathToTestFile(String fileName) {
        return System.getProperty("user.dir") + "/src/test/resources/data/testFiles/" + fileName;
    }
}
