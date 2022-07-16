package pages.commonComponents;

import com.codeborne.selenide.Condition;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class TopBarComponent extends BasePage {
    private String XP_TOP_PROF_LINK = "#top_profile_link";

    public void topProfileLinkShouldBePresent() {
        $(XP_TOP_PROF_LINK).shouldBe(Condition.exist);
    }
}
