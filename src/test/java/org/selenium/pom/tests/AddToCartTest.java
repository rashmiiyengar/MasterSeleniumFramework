package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataproviders.MyDataProvider;
import org.selenium.pom.extentreports.ExtentReport;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        ExtentReport.createTest("addToCartFromStorePage");
        Product product = new Product(1215);

        CartPage cartpage = new StorePage(getDriver()).
                load().getProductThumbnail().
                clickAddToCartButton(product.getName()).
                clickViewCarkLink();

        Assert.assertEquals(cartpage.getProductName(),product.getName());

    }

    @Test
    public void addFeaturedProductToCart() throws IOException {
        ExtentReport.createTest("addFeaturedProductToCart");
        Product product = new Product(1215);

       new HomePage(getDriver()).
                load().getProductThumbnail().clickAddToCartButton(product.getName()).clickViewCarkLink();


    }

    @Test
    public void addToCartFromProductPage() throws IOException {
        ExtentReport.createTest("addToCartFromProductPage");
        Product product = new Product(1215);
        String productNameSeparatedByDash = product.getName().toLowerCase().replaceAll(" ","-");

        ProductPage productPage =new ProductPage(getDriver()).loadProduct(productNameSeparatedByDash).addToCartFromProduct();
        Assert.assertTrue(productPage.getAlert().contains("“" + product.getName() +"” has been added to your cart."));

    }

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class)
    public  void addToCartFeaturedProducts(Product product){
        ExtentReport.createTest("addToCartFeaturedProducts");
        CartPage cartpage = new HomePage(getDriver()).load().
                getProductThumbnail().
                clickAddToCartButton(product.getName()).
                clickViewCarkLink();

        Assert.assertEquals(cartpage.getProductName(),product.getName());

    }


}