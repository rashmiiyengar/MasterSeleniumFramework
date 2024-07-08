package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataproviders.MyDataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Locale;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);

        CartPage cartpage = new StorePage(getDriver()).
                load().
                clickAddtoCartBtn("Blue Shoes").
                clickViewCarkLink();

        Assert.assertEquals(cartpage.getProductName(),product.getName());

    }

    @Test
    public void addFeaturedProductToCart() throws IOException {
        Product product = new Product(1215);

       new HomePage(getDriver()).
                load().
                clickAddToCartButtonFromHomePage(product.getName());

    }

    @Test
    public void addToCartFromProductPage() throws IOException {
        Product product = new Product(1215);
        String productNameSeparatedByDash = product.getName().toLowerCase().replaceAll(" ","-");

        ProductPage productPage =new ProductPage(getDriver()).loadProduct(productNameSeparatedByDash).addToCartFromProduct();
        Assert.assertTrue(productPage.getAlert().contains("“" + product.getName() +"” has been added to your cart."));

    }

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class)
    public  void addToCartFeaturedProducts(Product product){
        CartPage cartpage = new HomePage(getDriver()).load().clickAddToCartButtonFromHomePage(product.getName()).clickViewCarkLink();

        Assert.assertEquals(cartpage.getProductName(),product.getName());

    }


}