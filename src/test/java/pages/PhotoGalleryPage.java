package pages;

import static com.codeborne.selenide.Selenide.$x;

public class PhotoGalleryPage extends BasePage {
    private final String XP_LAST_PHOTO = "//div[@id='photos_choose_rows']/a";

    public void selectLatestPhotoFromGallery() {
        $x(XP_LAST_PHOTO).click();
    }
}
