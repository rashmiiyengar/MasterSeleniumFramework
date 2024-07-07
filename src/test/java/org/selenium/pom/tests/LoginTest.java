package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckout() throws InterruptedException {

        SignUpApi signUpApi = new SignUpApi();
        String userName = "demoUser" + new FakerUtils().generateRandomName();

        User user = new User().
                setUsername(userName).
                setPassword(userName).
                setEmail(userName + "@gmail.com");

        signUpApi.register(user);

        CartApi cartApi = new CartApi(); // customer not logged in with empty constructor
        Product product = null;
        try {
            product = new Product(1215);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cartApi.addToCart(product.getId(),1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(5000);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        Thread.sleep(5000);
        checkoutPage.clickLoginLink().
                login(user.getUsername(),user.getPassword()).
                clickLoginButton();
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
    }
}
