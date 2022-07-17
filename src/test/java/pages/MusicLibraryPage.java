package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class MusicLibraryPage extends BasePage {
    private final String XP_GLOBAL_SEARCH_TTL = "//div[@class='ape_list_header'][normalize-space(.)='Найдено в глобальном поиске']";
    private final String XP_ATTACH_BTN = "//div[contains(@class,'ape_audio_item')][.//a[contains(@class,'audio_row__title_inner')][normalize-space(.)='%s']]/div[text()='Прикрепить']";

    public void waitUntilSearchIsDone() {
        $x(XP_GLOBAL_SEARCH_TTL).shouldBe(Condition.visible);
    }

    public void attachSong(String songName) {
        $x(String.format(XP_ATTACH_BTN, songName)).click();
    }
}
