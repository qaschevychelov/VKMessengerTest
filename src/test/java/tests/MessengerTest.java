package tests;


import com.codeborne.selenide.Selenide;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class MessengerTest extends BaseTest {
    private String who = dataManager.getData("chat.who");
    private String message = dataManager.getData("chat.msg");
    private String photoName = dataManager.getData("photo.name");

    @Owner("Чевычелов Сергей")
    @Test(description = "Тест на создание чата")
    public void createChat() {
        baseSteps.auth(user.getPhone(), user.getPass());
        baseSteps.goToMessages();

        messengerSteps.waitForChatListVisible();
        messengerSteps.removeChatIfPresent(who);

        messengerSteps.createChat(who);

        // проверим, что чат создался
        messengerSteps.chatBodyShouldBeVisible(who);
        messengerSteps.sendMsg(message);
        baseSteps.clickLink("Назад");
        messengerSteps.checkIfChatHasCreated(who);
        Selenide.refresh();
        messengerSteps.checkIfChatHasCreated(who);
    }

    @Owner("Чевычелов Сергей")
    @Test(description = "Тест на аттач существующего фото")
    public void attachExistedPhotoTest() {
        baseSteps.auth(user.getPhone(), user.getPass());
        baseSteps.goToMessages();

        messengerSteps.waitForChatListVisible();
        messengerSteps.removeChatIfPresent(who);

        messengerSteps.createChat(who);
        messengerSteps.attachExistedPhoto();
        messengerSteps.clickSend();

        // проверим, что фото отправилось
        messengerSteps.photoMessageInChatShouldBeVisible();
    }

    @Owner("Чевычелов Сергей")
    @Test(description = "Тест на аттач фото из проводника")
    public void attachNewPhotoTest() {
        baseSteps.auth(user.getPhone(), user.getPass());
        baseSteps.goToMessages();

        messengerSteps.waitForChatListVisible();
        messengerSteps.removeChatIfPresent(who);

        messengerSteps.createChat(who);
        messengerSteps.attachMediaFile(dataManager.getPathToTestFile(photoName));
        messengerSteps.clickSend();

        // проверим, что фото отправилось
        messengerSteps.photoMessageInChatShouldBeVisible();
    }

    @Owner("Чевычелов Сергей")
    @Test(description = "Тест на отправку песни")
    public void attachMusicTest() {
        String songName = dataManager.getData("song.name");

        baseSteps.auth(user.getPhone(), user.getPass());
        baseSteps.goToMessages();

        messengerSteps.waitForChatListVisible();
        messengerSteps.removeChatIfPresent(who);

        messengerSteps.createChat(who);
        messengerSteps.attachMusic(songName);

        // проверим, что песня отправилась
        messengerSteps.songInChatShouldBeVisible(songName);
    }

    @Owner("Чевычелов Сергей")
    @Test(description = "Тест на отправку видео из библиотеки")
    public void attachExistedVideoTest() {
        String videoName = dataManager.getData("video.name");

        baseSteps.auth(user.getPhone(), user.getPass());
        baseSteps.goToMessages();

        messengerSteps.waitForChatListVisible();
        messengerSteps.removeChatIfPresent(who);

        messengerSteps.createChat(who);
        messengerSteps.attachVideo(videoName);

        // проверим, что видео отправилось
        messengerSteps.videoInChatShouldBeVisible(videoName);
    }

    @Owner("Чевычелов Сергей")
    @Test(description = "Тест на отправку видео из проводника")
    public void attachNewVideoTest() {
        String videoName = dataManager.getData("local.video.name");

        baseSteps.auth(user.getPhone(), user.getPass());
        baseSteps.goToMessages();

        messengerSteps.waitForChatListVisible();
        messengerSteps.removeChatIfPresent(who);

        messengerSteps.createChat(who);
        messengerSteps.attachMediaFile(dataManager.getPathToTestFile(videoName));
        messengerSteps.clickSend();

        // проверим, что видео отправилось
        messengerSteps.videoInChatShouldBeVisible("Открыть видео");
    }

    @Owner("Чевычелов Сергей")
    @Test(description = "Тест на отправку файла из проводника")
    public void attachNewFileTest() {
        String fileName = dataManager.getData("text.file.name");

        baseSteps.auth(user.getPhone(), user.getPass());
        baseSteps.goToMessages();

        messengerSteps.waitForChatListVisible();
        messengerSteps.removeChatIfPresent(who);

        messengerSteps.createChat(who);
        messengerSteps.attachFile(dataManager.getPathToTestFile(fileName));

        // проверим, что файл отправился
        messengerSteps.fileInChatShouldBeVisible(fileName);
    }
}
