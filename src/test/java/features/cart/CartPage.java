package features.cart;

import base.BasePage;
import features.check_out_step_one.CheckOutStepOnePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;


public class CartPage extends BasePage {

    private final By cartItem = By.className("cart_item");
    private final By removeBtn = By.xpath("//button[contains(text(), 'Remove')]");
    private final By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isItemInCart() {

        return driver.findElements(cartItem).size() > 0;
    }

    @Step("remove")
    public CartPage removeProduct() {

        try {
            clickWithClickableConditions(removeBtn);
        } catch (TimeoutException e) {
            System.out.println("We haven't products");
        }
        return this;
    }

    public CheckOutStepOnePage openCheckOutPage() {
        clickWithClickableConditions(checkoutBtn);
        return new CheckOutStepOnePage(driver);
    }

}
