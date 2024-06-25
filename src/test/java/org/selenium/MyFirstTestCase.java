package org.selenium;

import org.bouncycastle.util.Store;
import org.openqa.selenium.By;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.pages.*;
import org.selenium.pom.utils.JacksonUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyFirstTestCase extends BaseTest {


    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        HomePage homePage = new HomePage(driver).load();
        StorePage storePage= homePage.navigateToStoreUsingMenu();
        storePage.search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");
        Thread.sleep(2000);
        storePage.clickAddtoCartBtn("Blue Shoes");
        Thread.sleep(5000);
        CartPage cartpage = storePage.clickViewCarkLink();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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

    @Test
    public void loginAndCheckoutToConfirmation() throws InterruptedException, IOException {
        //using POJO Object

//        billingAddress.setFirstName("Sairam1").
//                setLastName("ram").
//                setAddressLineOne("chulavista").
//                setCity("montana").
//                setPostalCode("56567").
//                setEmail("sairam@gmail.com");

        BillingAddress billingAddress=JacksonUtil.deserializeJson("myBillingSAddress.json",BillingAddress.class);

        HomePage homePage = new HomePage(driver).load();
        StorePage storePage= homePage.navigateToStoreUsingMenu();
        storePage.search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");
        Thread.sleep(2000);
        storePage.clickAddtoCartBtn("Blue Shoes");
        Thread.sleep(5000);
        CartPage cartpage = storePage.clickViewCarkLink();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(cartpage.getProductName(),"Blue Shoes");
        CheckoutPage checkoutPage = cartpage.clickCheckoutBtn().
                setBillingAddress(billingAddress);
        Thread.sleep(2000);
        ConfirmationPage confirmationPage = checkoutPage.clickPlaceOrder();
    }
}
