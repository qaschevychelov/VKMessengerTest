package steps;

import pages.MessengerPage;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class MessengerSteps {
    private MessengerPage messengerPage = new MessengerPage();

    public void waitForChatListVisible() {
        messengerPage.chatListShouldBeVisible();
    }

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

    public void createChat(String withWho) {
        messengerPage.clickNewChatBtn();
        messengerPage.searchUser(withWho);
        messengerPage.selectNewChat(withWho);
        messengerPage.clickBtn("Перейти к диалогу");
    }

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

    public void sendMsg(String message) {
        messengerPage.sendMsg(message);
        messengerPage.messageInChatShouldBeVisible(message);
    }

    public void checkIfChatHasCreated(String who) {
        messengerPage.chatListShouldBeVisible();
        List<String> names = messengerPage.getChatNames();
        assertTrue(names.contains(who), "Чат с " + who + " не создался");
        messengerPage.chatShouldBeVisibleInList(who);
    }

    public void attachExistedPhoto() {
        messengerPage.attachMedia("Фотография");
        messengerPage.popUpShouldBeVisible();
        messengerPage.selectLatestPhotoFromGallery();
        messengerPage.popUpShouldBeHidden();
    }

    public void clickSend() {
        messengerPage.clickSend();
    }

    public void photoMessageInChatShouldBeVisible() {
        messengerPage.photoMessageInChatShouldBeVisible();
    }

    public void attachFile(String filePath) {
        messengerPage.attachFileFromFileSystem(filePath);
        messengerPage.removeAttachBtnShouldBeVisible();
    }
}
