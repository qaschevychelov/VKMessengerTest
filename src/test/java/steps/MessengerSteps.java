package steps;

import io.qameta.allure.Step;
import pages.MessengerPage;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class MessengerSteps {
    private MessengerPage messengerPage = new MessengerPage();

    @Step("Список чатов отображается")
    public void waitForChatListVisible() {
        messengerPage.chatListShouldBeVisible();
    }

    @Step("Удалим чат с {withWho}, если он есть")
    public void removeChatIfPresent(String withWho) {
        List<String> names = messengerPage.getChatNames();
        if (names.contains(withWho)) {
            messengerPage.clickChatAction(withWho, "Очистить историю сообщений");
            messengerPage.popUpShouldBeVisible();
            messengerPage.clickBtn("Удалить");
            messengerPage.popUpShouldBeHidden();

            names = messengerPage.getChatNames();
            assertFalse(names.contains(withWho), "Чат с " + withWho + " не удалился");
        }
    }

    @Step("Создадим чат с {withWho}")
    public void createChat(String withWho) {
        messengerPage.clickNewChatBtn();
        messengerPage.searchUser(withWho);
        messengerPage.selectNewChat(withWho);
        messengerPage.clickBtn("Перейти к диалогу");
    }

    @Step("Окно открытого чата отображается (основные элементы отображаются)")
    public void chatBodyShouldBeVisible(String who) {
        messengerPage.chatBodyShouldBeVisible(who);
        messengerPage.linkShouldBeVisible("Назад");
        messengerPage.callBtnShouldBeVisible();
        messengerPage.chatOptionsBtnShouldBeVisible();
        messengerPage.chatImgShouldBeVisible();
        messengerPage.chatAttachBtnShouldBeVisible();
        messengerPage.newMessageInputShouldbeVisible();
        messengerPage.newVoiceMessageBtnShouldbeVisible();
    }

    @Step("Отправим вообщение {message}")
    public void sendMsg(String message) {
        messengerPage.sendMsg(message);
        messengerPage.messageInChatShouldBeVisible(message);
    }

    @Step("Чат с {who} создался")
    public void checkIfChatHasCreated(String who) {
        messengerPage.chatListShouldBeVisible();
        List<String> names = messengerPage.getChatNames();
        assertTrue(names.contains(who), "Чат с " + who + " не создался");
        messengerPage.chatShouldBeVisibleInList(who);
    }

    @Step("Прикрепим фото из галереи")
    public void attachExistedPhoto() {
        messengerPage.attachMedia("Фотография");
        messengerPage.popUpShouldBeVisible();
        messengerPage.selectLatestPhotoFromGallery();
        messengerPage.popUpShouldBeHidden();
    }

    @Step("Кликнем Отправить")
    public void clickSend() {
        messengerPage.clickSend();
    }

    @Step("Сообщение с фото отображается в чате")
    public void photoMessageInChatShouldBeVisible() {
        messengerPage.photoMessageInChatShouldBeVisible();
    }

    @Step("Прикрепим файл из файловой системы")
    public void attachFile(String filePath) {
        messengerPage.attachFileFromFileSystem(filePath);
        messengerPage.removeAttachBtnShouldBeVisible();
    }
}
