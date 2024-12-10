package features.check_out_complete;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutCompletePage extends BasePage {

    private final By title = By.xpath("//span[@class='title']");

    public CheckOutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        return getElementText(title);
    }
}
