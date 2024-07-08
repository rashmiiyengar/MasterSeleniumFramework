package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.*;
import org.selenium.pom.utils.JacksonUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {

   // @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        HomePage homePage = new HomePage(getDriver()).load();
        StorePage storePage= homePage.getHeaderComponent().navigateToStoreUsingMenu();
        storePage.search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");
        storePage.getProductThumbnail().clickAddToCartButton("Blue Shoes");

        CartPage cartpage = storePage.getProductThumbnail().clickViewCarkLink();

        Assert.assertEquals(cartpage.getProductName(),"Blue Shoes");
        CheckoutPage checkoutPage = cartpage.clickCheckoutBtn();
        checkoutPage.
                sendFirstName("Sai").
                sendLastName("Ram").
                sendBillingCompany("Nexus Tech").
                sendBillingAddress("MUMBAI").
                sendBillingCity("baroda").
                sendBillingPostcode("982345").
                sendBillingPhone("7585467544").
                sendBillingEmail("sai@gmail.com");
                Thread.sleep(2000);
                ConfirmationPage confirmationPage = checkoutPage.clickPlaceOrder();
    }

   // @Test
    public void checkoutToConfirmation() throws InterruptedException, IOException {


        BillingAddress billingAddress=JacksonUtil.deserializeJson("myBillingSAddress.json",BillingAddress.class);
        Product product = new Product(1215);

        HomePage homePage = new HomePage(getDriver()).load();
        StorePage storePage= homePage.getHeaderComponent().navigateToStoreUsingMenu();
        storePage.search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");
        Thread.sleep(2000);
        //storePage.clickAddtoCartBtn("Blue Shoes");
        storePage.getProductThumbnail().clickAddToCartButton(product.getName());
        Thread.sleep(5000);
        CartPage cartpage = storePage.getProductThumbnail().clickViewCarkLink();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(cartpage.getProductName(),product.getName());
        CheckoutPage checkoutPage = cartpage.
                clickCheckoutBtn().
                    setBillingAddress(billingAddress).
                        selectDirectBankTransfer();
        Thread.sleep(2000);
        ConfirmationPage confirmationPage = checkoutPage.clickPlaceOrder();
    }
}

