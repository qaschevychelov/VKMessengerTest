package pages.commonComponents;

import pages.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class SideBarComponent extends BasePage {
    private String XP_MSG = "#l_msg a";

    public void clickMessages() {
        $(XP_MSG).click();
    }
}
