package steps;

import pages.BasePage;
import pages.MessengerPage;
import pages.commonComponents.SideBarComponent;
import pages.commonComponents.TopBarComponent;

public class BaseSteps {
    private BasePage basePage = new BasePage();
    private TopBarComponent topBarComponent = new TopBarComponent();
    private SideBarComponent sideBarComponent = new SideBarComponent();

    public void auth(String phone, String pass) {
        basePage.clickBtn("Войти");
        basePage.setInput("Телефон или почта", phone);
        basePage.clickBtn("Продолжить");
        basePage.setInput("Введите пароль", pass);
        basePage.clickBtn("Продолжить");

        topBarComponent.topProfileLinkShouldBePresent();

        if (basePage.isPopUpVisible()) basePage.closePopUp();
    }

    public void goToMessages() {
        sideBarComponent.clickMessages();
    }

    public void clickLink(String link) {
        basePage.clickLink(link);
    }
}
