package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.*;
import org.selenium.pom.utils.JacksonUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginAndCheckoutTest extends BaseTest {

    @Test
    public void loginWithUserAndCheckout() throws InterruptedException, IOException {

        BillingAddress billingAddress= JacksonUtil.deserializeJson("myBillingSAddress.json",BillingAddress.class);
        Product product = new Product(1215);
        User user = new User("SaiRam1","Sairam1");

        HomePage homePage = new HomePage(driver).load();
        StorePage storePage= homePage.navigateToStoreUsingMenu();
        storePage.search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");
        Thread.sleep(2000);
        //storePage.clickAddtoCartBtn("Blue Shoes");
        storePage.clickAddtoCartBtn(product.getName());
        Thread.sleep(5000);
        CartPage cartpage = storePage.clickViewCarkLink();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(cartpage.getProductName(),product.getName());
        CheckoutPage checkoutPage = cartpage.
                clickCheckoutBtn();
        checkoutPage.clickLoginLink();
        Thread.sleep(3000);
        checkoutPage.login(user.getUsername(),user.getPassword()).clickLoginButton().setBillingAddress(billingAddress);
        Thread.sleep(2000);
        ConfirmationPage confirmationPage = checkoutPage.clickPlaceOrder();
    }
}
