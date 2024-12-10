package features.products;

import base.BaseTest;
import features.cart.CartPage;
import features.check_out_complete.CheckOutCompletePage;
import features.check_out_step_one.CheckOutStepOnePage;
import features.check_out_step_two.CheckOutStepTwoPage;
import features.product_detail.ProductDetailsPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TokenManager;

import java.io.IOException;

public class ProductsPageTest extends BaseTest {


    @Test(priority = 0,
            groups = "products")
    @Description("successful open cart page")
    public void openCartPage() throws IOException {
        //  Preparing
        var tokenManager = new TokenManager(driver);
        navigateTo(constants.AUTHORIZE_PAGE_URL);
        tokenManager.loadTokenFromFile(constants.tokenName, constants.authorisePageDomain, constants.tokenPath);

        var productsPage = new ProductsPage(driver);
        var cartPage = new CartPage(driver);

        navigateTo(constants.PRODUCTS_PAGE_URL);

        // Steps
        productsPage
                .addToCart(0)
                .openCartPage();

        // Assert
        Assert.assertTrue(cartPage.isItemInCart());
    }


    @Test(priority = 0,
            groups = "products")
    public void filterPriceFromLowToHight() throws IOException {

        //  Preparing
        var tokenManager = new TokenManager(driver);
        navigateTo(constants.AUTHORIZE_PAGE_URL);
        tokenManager.loadTokenFromFile(constants.tokenName, constants.authorisePageDomain, constants.tokenPath);

        var productsPage = new ProductsPage(driver);
        navigateTo(constants.PRODUCTS_PAGE_URL);

        // steps
        productsPage
                .filterProductsFromLowToHeightPrice(constants.SELECTOR_FROM_LOW_TO_HIGHT_PRICE_VALUE);

        // asserts
        Assert.assertEquals(constants.SELECTOR_FROM_LOW_TO_HIGHT_PRICE_TEXT, productsPage.getCurrentSelectedValue());
    }


    @Test(priority = 0,
            groups = "products")
    public void openProductDetailPage() throws IOException, InterruptedException {
        //  Preparing
        var tokenManager = new TokenManager(driver);
        navigateTo(constants.AUTHORIZE_PAGE_URL);
        tokenManager.loadTokenFromFile(constants.tokenName, constants.authorisePageDomain, constants.tokenPath);

        var productsPage = new ProductsPage(driver);
        var productsDetailPage = new ProductDetailsPage(driver);
        navigateTo(constants.PRODUCTS_PAGE_URL);

        // steps
        productsPage.openDetailsPage(0);

        // assert
        Assert.assertNotEquals("ITEM NOT FOUND", productsDetailPage.getProductName());
    }

    @Test(priority = 1,
            groups = "products")
    public void successfulOrderProcessing() throws IOException {

        //  Preparing
        var tokenManager = new TokenManager(driver);
        navigateTo(constants.AUTHORIZE_PAGE_URL);
        tokenManager.loadTokenFromFile(constants.tokenName, constants.authorisePageDomain, constants.tokenPath);

        var productsPage = new ProductsPage(driver);
        var cartPage = new CartPage(driver);
        var checkOutStepOnePage = new CheckOutStepOnePage(driver);
        var checkOutStepTwoPage = new CheckOutStepTwoPage(driver);
        var checkOutCompletePage = new CheckOutCompletePage(driver);

        navigateTo(constants.PRODUCTS_PAGE_URL);
        productsPage
                .addToCart(0);

        // steps
        productsPage
                .openCartPage();
        cartPage
                .openCheckOutPage();
        checkOutStepOnePage
                .enterFirstname(constants.CHECKOUT_FIRST_STEP_PAGE_FIRSTNAME)
                .enterLastName(constants.CHECKOUT_FIRST_STEP_PAGE_LASTNAME)
                .enterZip(constants.CHECKOUT_FIRST_STEP_PAGE_ZIP)
                .openCheckOutStepTwoPage();
        checkOutStepTwoPage
                .completeThePurchase();


        // Assert
        Assert.assertEquals(constants.CHECKOUT_COMPLETE_TITLE_TEXT_VALUE, checkOutCompletePage.getTitleText());

    }

    @Test(priority = 1,
            groups = "products")
    @Description("success logout")
    public void successLogOut() throws IOException {
        //  Preparing
        var tokenManager = new TokenManager(driver);
        navigateTo(constants.AUTHORIZE_PAGE_URL);
        tokenManager.loadTokenFromFile(constants.tokenName, constants.authorisePageDomain, constants.tokenPath);

        var productsPage = new ProductsPage(driver);
        navigateTo(constants.PRODUCTS_PAGE_URL);

        //steps
        productsPage
                .openBurgerMenu()
                .logout();
        var currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(constants.AUTHORIZE_PAGE_URL, currentUrl);

    }
}
