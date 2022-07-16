package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.*;
import steps.BaseSteps;
import steps.MessengerSteps;
import utils.DataManager;
import utils.Screenshot;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

@Listeners({Screenshot.class})
public class BaseTest {
    public DataManager dataManager = new DataManager();
    public User user;

    public BaseSteps baseSteps = new BaseSteps();
    public MessengerSteps messengerSteps = new MessengerSteps();

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        // так как это тестовое задание и тестов мало, добавлю открытие браузера прям сюда
        open(dataManager.getData("auth.url"));
    }

    @BeforeSuite
    public void getUser() {
        String json = dataManager.getUsers();
        List<Map> list = JsonPath.from(json).getList("users", Map.class);

        // здесь по-хорошему нужно считывать всех юзеров в список
        // но у нас юзер всего 1. Поэтому просто возьму сразу [0] юзера

        user = new User(
                new String(Base64.getDecoder().decode(list.get(0).get("phone").toString())),
                new String(Base64.getDecoder().decode(list.get(0).get("pass").toString()))
        );
    }

    @AfterMethod
    public void quit() {
        Selenide.closeWebDriver();
    }
}
