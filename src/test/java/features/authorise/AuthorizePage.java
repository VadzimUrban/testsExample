package features.authorise;

import base.BasePage;
import features.products.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthorizePage extends BasePage {


    private final By usernameInputField = By.id("user-name");
    private final By passwordInputField = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By errorMessage = By.className("error-message-container");


    public AuthorizePage(WebDriver driver) {
        super(driver);
    }

    public AuthorizePage enterUserName(String username) {
        enterTextWithVisibilityConditions(usernameInputField, username);
        return this;
    }

    public AuthorizePage enterPassword(String password) {
        enterTextWithVisibilityConditions(passwordInputField, password);
        return this;
    }

    public ProductsPage confirmСredentials() {
        clickWithClickableConditions(loginBtn);
        return new ProductsPage(driver);
    }

    public Boolean isErrorMessageVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
    }

}
