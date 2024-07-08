package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {

    @Test
    public void GuestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress= JacksonUtil.deserializeJson("myBillingSAddress.json",BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi = new CartApi();

        cartApi.addToCart(1215,1);
        injectCookiesToBrowser(cartApi.getCookies());

        checkoutPage.load().setBillingAddress(billingAddress).clickPlaceOrder();

        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");

    }

    @Test
    public void LoginAndCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress= JacksonUtil.deserializeJson("myBillingSAddress.json",BillingAddress.class);
        SignUpApi signUpApi = new SignUpApi();
        String userName = "demoUser" + new FakerUtils().generateRandomName();

        User user = new User().
                setUsername(userName).
                setPassword(userName).
                setEmail(userName + "@gmail.com");

        signUpApi.register(user);

        CartApi cartApi = new CartApi(signUpApi.getCookies()); // customer not logged in with empty constructor
        Product product = null;
        try {
            product = new Product(1215);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cartApi.addToCart(product.getId(),1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage.load();

        checkoutPage.load().setBillingAddress(billingAddress).clickPlaceOrder();

        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }

    @Test
    public void GuestCheckoutUsingCashOnDelivary() throws IOException {
        BillingAddress billingAddress= JacksonUtil.deserializeJson("myBillingSAddress.json",BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1209,2);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load().setBillingAddress(billingAddress).selectCashOnDeliveryMethod().clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");

    }

    @Test
    public void LoginAndCheckoutUsingCashOnDelivary() throws IOException {
        BillingAddress billingAddress= JacksonUtil.deserializeJson("myBillingSAddress.json",BillingAddress.class);
        SignUpApi signUpApi = new SignUpApi();
        String userName = "demoUser" + new FakerUtils().generateRandomName();

        User user = new User().
                setUsername(userName).
                setPassword(userName).
                setEmail(userName + "@gmail.com");

        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage.load();

        checkoutPage.setBillingAddress(billingAddress).
                selectCashOnDeliveryMethod().
                clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }
}
