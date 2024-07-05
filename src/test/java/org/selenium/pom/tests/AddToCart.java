package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class AddToCart extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);

        CartPage cartpage = new StorePage(getDriver()).
                load().
                clickAddtoCartBtn("Blue Shoes").
                clickViewCarkLink();

        Assert.assertEquals(cartpage.getProductName(),product.getName());

    }

}