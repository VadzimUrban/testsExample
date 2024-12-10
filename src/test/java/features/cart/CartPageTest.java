package features.cart;

import base.BasePage;
import base.BaseTest;
import features.products.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TokenManager;

import java.io.IOException;

public class CartPageTest extends BaseTest {

    @Test
    public void removeProductFromCart() throws IOException {

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

        cartPage
                .removeProduct();

        // Assert
        Assert.assertFalse(cartPage.isItemInCart());
    }
}
