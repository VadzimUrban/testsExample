package features.authorise;

import base.BaseTest;
import features.products.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TokenManager;

import java.io.IOException;


public class AuthorizePageTest extends BaseTest {

    @Test
    public void loginAndSaveToken() throws IOException {

        var authorizePage = new AuthorizePage(driver);
        navigateTo(constants.AUTHORIZE_PAGE_URL);

        // Steps
        authorizePage
                .enterUserName(constants.CORRECT_USER_NAME)
                .enterPassword(constants.CORRECT_PASSWORD)
                .confirmСredentials();


        var productsPage = new ProductsPage(driver);
        Assert.assertEquals(constants.PRODUCTS_TITLE_TEXT_VALUE, productsPage.getTitleText());

        var tokenManager = new TokenManager(driver);
        tokenManager.saveTokenToFile(constants.tokenName, constants.tokenPath);
    }


    @Test
    public void authorizeWithCorrectCredentials() {

        //Preparing
        navigateTo(constants.AUTHORIZE_PAGE_URL);
        var authorizePage = new AuthorizePage(driver);

        // Steps
        authorizePage
                .enterUserName(constants.CORRECT_USER_NAME)
                .enterPassword(constants.CORRECT_PASSWORD)
                .confirmСredentials();

        // Assert
        var productsPage = new ProductsPage(driver);
        Assert.assertEquals(constants.PRODUCTS_TITLE_TEXT_VALUE, productsPage.getTitleText());
    }


    @Test
    public void authorizeWithIncorrectUsername() {

        //Preparing
        navigateTo(constants.AUTHORIZE_PAGE_URL);
        var authorizePage = new AuthorizePage(driver);

        // Steps
        authorizePage
                .enterUserName(constants.INCORRECT_USER_NAME)
                .enterPassword(constants.CORRECT_PASSWORD)
                .confirmСredentials();

        // Result
        Assert.assertTrue(authorizePage.isErrorMessageVisible());
    }


    @Test
    public void authorizeWithIncorrectPassword() {

        //Preparing
        navigateTo(constants.AUTHORIZE_PAGE_URL);
        var authorizePage = new AuthorizePage(driver);

        // Steps
        authorizePage
                .enterUserName(constants.CORRECT_USER_NAME)
                .enterPassword(constants.INCORRECT_PASSWORD)
                .confirmСredentials();

        // Assert
        Assert.assertTrue(authorizePage.isErrorMessageVisible());
    }
}
