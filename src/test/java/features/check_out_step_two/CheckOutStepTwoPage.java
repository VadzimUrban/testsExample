package features.check_out_step_two;

import base.BasePage;
import features.check_out_complete.CheckOutCompletePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutStepTwoPage extends BasePage {

    private final By finishBtn = By.id("finish");

    public CheckOutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public CheckOutCompletePage completeThePurchase() {
        scrollToTheElement(finishBtn);
        clickWithClickableConditions(finishBtn);
        return new CheckOutCompletePage(driver);
    }
}
