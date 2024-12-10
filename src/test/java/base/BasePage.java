package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final org.slf4j.Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Единый WebDriverWait
    }

    protected void enterTextWithVisibilityConditions(By by, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(text);
    }

    protected void clickWithClickableConditions(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    protected void clickWithClickableConditions(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void scrollToTheElement(By by) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
    }

    protected void scrollToTheElement(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void selectOptionByValue(String optionValue, By by) {

        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(by)));
        select.selectByValue(optionValue);

    }

    protected String getCurrentSelectedValue(By by) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(by)));
        return select.getFirstSelectedOption().getText();
    }

    protected WebElement findAndScrollToTheElement(By by, Integer index) {
        WebElement btn = driver.findElements(by).get(index);
        scrollToTheElement(btn);
        return btn;
    }

    protected String getElementText(By by) {
        return driver.findElement(by).getText();
    }
}
