package pages;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;

public class FileSearchPage extends BasePage {
    private final String XP_BROWSE_FILE = "#choose_doc_upload";

    public void uploadFileFromDisk(String fileName) {
        $(XP_BROWSE_FILE).uploadFile(new File(fileName));
    }
}
