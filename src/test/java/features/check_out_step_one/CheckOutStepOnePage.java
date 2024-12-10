package features.check_out_step_one;

import base.BasePage;
import features.check_out_step_two.CheckOutStepTwoPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutStepOnePage extends BasePage {

    private final By firstNameInputField = By.id("first-name");
    private final By lastNameInputField = By.id("last-name");
    private final By zipInputField = By.id("postal-code");
    private final By continueBtn = By.id("continue");

    public CheckOutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public CheckOutStepOnePage enterFirstname(String firstName) {
        scrollToTheElement(firstNameInputField);
        enterTextWithVisibilityConditions(firstNameInputField, firstName);
        return this;
    }

    public CheckOutStepOnePage enterLastName(String lastname) {
        scrollToTheElement(lastNameInputField);
        enterTextWithVisibilityConditions(lastNameInputField, lastname);
        return this;
    }

    public CheckOutStepOnePage enterZip(String zip) {
        scrollToTheElement(zipInputField);
        enterTextWithVisibilityConditions(zipInputField, zip);
        return this;
    }

    public CheckOutStepTwoPage openCheckOutStepTwoPage() {
        scrollToTheElement(continueBtn);
        clickWithClickableConditions(continueBtn);
        return new CheckOutStepTwoPage(driver);
    }
}
