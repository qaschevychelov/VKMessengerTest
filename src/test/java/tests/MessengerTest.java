package tests;


import com.codeborne.selenide.Selenide;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class MessengerTest extends BaseTest {
    private String who = dataManager.getData("chat.who");
    private String message = dataManager.getData("chat.msg");

    @Owner("Чевычелов Сергей")
    @Test(description = "Тест на создание чата")
    public void createChat() {
        baseSteps.auth(user.getPhone(), user.getPass());
        baseSteps.goToMessages();

        messengerSteps.waitForChatListVisible();
        messengerSteps.removeChatIfPresent(who);

        messengerSteps.createChat(who);

        // проверим что чат создался
        messengerSteps.chatBodyShouldBeVisible(who);
        messengerSteps.sendMsg(message);
        baseSteps.clickLink("Назад");
        messengerSteps.checkIfChatHasCreated(who);
        Selenide.refresh();
        messengerSteps.checkIfChatHasCreated(who);
    }
}
