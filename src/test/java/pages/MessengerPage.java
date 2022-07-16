package pages;

import com.codeborne.selenide.Condition;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MessengerPage extends BasePage {
    // список чатов
    private final String XP_CHAT_LIST = "//ul[@id='im_dialogs']";
    private final String XP_CHAT_NAME = XP_CHAT_LIST + "//div[@class='nim-dialog--name']//span[@class='_im_dialog_link']";
    private final String XP_CHAT = XP_CHAT_LIST + "//li[@data-msgid][.//span[@class='_im_dialog_link'][text()='%s']]";
    private final String XP_CHAT_ACTIONS_BTN = XP_CHAT + "//button[contains(@class,'actions')]";
    private final String XP_CHAT_ACTIONS_MODAL = "//ul[@class='im-page--inline-menu-list']";
    private final String XP_CHAT_ACTIONS_MODAL_BTN = XP_CHAT_ACTIONS_MODAL + "//button[normalize-space(.)='%s']";

    // создание нового чата
    private final String XP_NEW_CHAT_BTN = "//button[@aria-label='Новый чат']";
    private final String XP_NEW_CHAT_SEARCH_INP = "//input[@id='im_dialogs_creation']";
    private final String XP_NEW_CHAT_SEARCH_ROW = "//a[contains(@class,'im-creation--item')]//div[@class='olist_item_name'][text()='%s']";

    // внутри чата
    private final String XP_CHAT_BODY = "//div[@class='im-page--chat-body']";
    private final String XP_CHAT_TTL = "//div[@class='im-page--title-wrapper']//a[text()='%s']";
    private final String XP_CHAT_CALL = "//div[@class='im-page--aside']//div[@aria-label='Позвонить']";
    private final String XP_CHAT_OPTS = "//div[@class='im-page--aside']//div[@aria-label='Действия']";
    private final String XP_CHAT_IMG = "//div[@class='im-page--aside-photo']//img";
    private final String XP_CHAT_ATTACH_BTN = "//a[@aria-label='Ещё']";
    private final String XP_CHAT_NEW_MSG = "//div[@contenteditable='true']";
    private final String XP_CHAT_NEW_VOICE_BTN = "//button[@aria-label='Голосовое сообщение']";
    private final String XP_CHAT_MSG = "//div[contains(@class,'im-mess-stack _im_mess_stack')]//li[.//div[text()='%s']][not(contains(@class,'im-mess_failed')) and not(contains(@class,'im-mess_sending'))]";


    public void chatListShouldBeVisible() {
        $x(XP_CHAT_LIST).shouldBe(Condition.visible);
    }

    public List<String> getChatNames() {
        return $$x(XP_CHAT_NAME).texts();
    }

    public void clickChatAction(String withWho, String action) {
        $x(String.format(XP_CHAT, withWho)).hover();
        $x(String.format(XP_CHAT_ACTIONS_BTN, withWho)).hover();
        $x(XP_CHAT_ACTIONS_MODAL).shouldBe(Condition.visible);
        $x(String.format(XP_CHAT_ACTIONS_MODAL_BTN, action)).click();
    }

    public void clickNewChatBtn() {
        $x(XP_NEW_CHAT_BTN).click();
    }

    public void searchUser(String who) {
        $x(XP_NEW_CHAT_SEARCH_INP).setValue(who);
    }

    public void selectNewChat(String withWho) {
        $x(String.format(XP_NEW_CHAT_SEARCH_ROW, withWho)).click();
    }

    public void chatBodyShouldBeVisible(String who) {
        $x(XP_CHAT_BODY).shouldBe(Condition.visible);
        $x(String.format(XP_CHAT_TTL, who)).shouldBe(Condition.visible);
    }

    public void callBtnShouldBeVisible() {
        $x(XP_CHAT_CALL).shouldBe(Condition.visible);
    }

    public void chatOptionsBtnShouldBeVisible() {
        $x(XP_CHAT_OPTS).shouldBe(Condition.visible);
    }

    public void chatImgShouldBeVisible() {
        $x(XP_CHAT_IMG).shouldBe(Condition.visible);
    }

    public void chatAttachBtnShouldBeVisible() {
        $x(XP_CHAT_ATTACH_BTN).shouldBe(Condition.visible);
    }

    public void newMessageInputShouldbeVisible() {
        $x(XP_CHAT_NEW_MSG).shouldBe(Condition.visible);
    }

    public void newVoiceMessageBtnShouldbeVisible() {
        $x(XP_CHAT_NEW_VOICE_BTN).shouldBe(Condition.visible);
    }

    public void sendMsg(String message) {
        $x(XP_CHAT_NEW_MSG).setValue(message).pressEnter();
    }

    public void chatShouldBeVisibleInList(String who) {
        $x(String.format(XP_CHAT, who)).shouldBe(Condition.visible);
    }

    public void messageInChatShouldBeVisible(String message) {
        $x(String.format(XP_CHAT_MSG, message)).shouldBe(Condition.visible);
    }
}
