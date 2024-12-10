package features.products;

import base.BasePage;
import features.authorise.AuthorizePage;
import features.cart.CartPage;
import features.product_detail.ProductDetailsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

    private final By addToCartBtn = By.xpath("//button[contains(text(), 'Add to cart')]");
    private final By title = By.xpath("//span[@class='title']");
    private final By openCartBtn = By.className("shopping_cart_link");
    private final By productFilterSelector = By.className("product_sort_container");
    private final By productName = By.className("inventory_item_name");
    private final By burgerMenuBtn = By.id("react-burger-menu-btn");
    private final By logoutBtn = By.id("logout_sidebar_link");


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step(value = "add product to cart")
    public ProductsPage addToCart(Integer index) {

        WebElement btn = driver.findElements(addToCartBtn).get(index);
        scrollToTheElement(btn);
        clickWithClickableConditions(btn);
        logger.info("added product to cart");
        return this;
    }

    @Step(value = "open cart page")
    public CartPage openCartPage() {
        clickWithClickableConditions(openCartBtn);
        logger.info("opened cart page");
        return new CartPage(driver);
    }

    public String getTitleText() {
        logger.info("got title text");
        return getElementText(title);

    }

    @Step(value = "filter products from low to height price")
    public ProductsPage filterProductsFromLowToHeightPrice(String optionValue) {
        selectOptionByValue(optionValue, productFilterSelector);
        logger.info("filter products from low to height price");
        return this;
    }

    public String getCurrentSelectedValue() {
        logger.info("got current selected value");
        return getCurrentSelectedValue(productFilterSelector);
    }

    @Step(value = "open details page")
    public ProductDetailsPage openDetailsPage(Integer index) {
        clickWithClickableConditions(findAndScrollToTheElement(productName, index));
        logger.info("opened details page");
        return new ProductDetailsPage(driver);
    }

    @Step(value = "open burger menu")
    public ProductsPage openBurgerMenu() {

        clickWithClickableConditions(burgerMenuBtn);
        logger.info("opened burger menu");
        return this;
    }

    @Step(value = "logout")
    public AuthorizePage logout() {
        clickWithClickableConditions(logoutBtn);
        logger.info("logout");
        return new AuthorizePage(driver);
    }
}
