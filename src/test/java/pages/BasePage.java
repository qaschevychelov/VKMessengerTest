package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class BasePage {
    private String XP_BTN = "//button[contains(@class,'FlatButton') or contains(@class,'vkc')][normalize-space(.)='%s']";
    private String XP_INP = "//input[contains(@class,'TextField')][@placeholder='%s']";
    private String XP_LINK = "//a[normalize-space(.)='%s']";
    private String XP_POPUP = "//div[@id='box_layer']";
    private String XP_POPUP_CLOSE_BTN = XP_POPUP + "//div[contains(@class,'closeButton')]";

    public void clickBtn(String btnName) {
        $x(String.format(XP_BTN, btnName)).click();
    }

    public void setInput(String inputName, String value) {
        $x(String.format(XP_INP, inputName)).setValue(value);
    }

    public boolean isPopUpVisible() {
        return $x(XP_POPUP).is(Condition.visible);
    }

    public void closePopUp() {
        $x(XP_POPUP_CLOSE_BTN).click();
        $x(XP_POPUP).shouldBe(Condition.hidden);
    }

    public void popUpShouldBeVisible() {
        $x(XP_POPUP).shouldBe(Condition.visible);
    }

    public void popUpShouldBeHidden() {
        $x(XP_POPUP).shouldBe(Condition.hidden);
    }

    public void linkShouldBeVisible(String link) {
        $x(String.format(XP_LINK, link)).shouldBe(Condition.visible);
    }

    public void clickLink(String link) {
        $x(String.format(XP_LINK, link)).click();
    }
}
