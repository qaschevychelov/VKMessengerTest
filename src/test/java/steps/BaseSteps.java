package steps;

import io.qameta.allure.Step;
import pages.BasePage;
import pages.commonComponents.SideBarComponent;
import pages.commonComponents.TopBarComponent;

public class BaseSteps {
    private BasePage basePage = new BasePage();
    private TopBarComponent topBarComponent = new TopBarComponent();
    private SideBarComponent sideBarComponent = new SideBarComponent();

    @Step("авторизуем юзера {phone}")
    public void auth(String phone, String pass) {
        basePage.clickBtn("Войти");
        basePage.setInput("Телефон или почта", phone);
        basePage.clickBtn("Продолжить");
        basePage.setInput("Введите пароль", pass);
        basePage.clickBtn("Продолжить");

        topBarComponent.topProfileLinkShouldBePresent();

        if (basePage.isPopUpVisible()) basePage.closePopUp();
    }

    @Step("Перейти в сообщения")
    public void goToMessages() {
        sideBarComponent.clickMessages();
    }

    @Step("Перейти по ссылке {link}")
    public void clickLink(String link) {
        basePage.clickLink(link);
    }
}
