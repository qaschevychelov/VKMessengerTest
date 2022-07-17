package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class VideoSearchPage extends BasePage {
    private final String XP_GLOBAL_SEARCH_TTL = "//div[contains(@class,'video_subtab_pane_search_global_videos')][contains(.,'Найдено')]";
    private final String XP_VIDEO = "//div[contains(@class,'video_item ')][.//a[@class='video_item_title'][normalize-space(.)='%s']]";

    public void waitUntilSearchIsDone() {
        $x(XP_GLOBAL_SEARCH_TTL).shouldBe(Condition.visible);
    }

    public void attachVideo(String videoName) {
        $x(String.format(XP_VIDEO, videoName)).click();
    }
}
