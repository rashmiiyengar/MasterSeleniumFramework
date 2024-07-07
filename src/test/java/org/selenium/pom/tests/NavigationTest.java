package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class NavigationTest extends BaseTest {

    @Test
    public void naviagteFromHomeToStoreUsingMainMenu(){

        StorePage storePage= new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu();

        Assert.assertEquals(storePage.getTitle(),"Store");
    }

    @Test
    public void navigateFromStoreToProductPage() throws IOException {
        Product product = new Product(1215);

        ProductPage productPage= new StorePage(getDriver()).
                load().navigateToTheProductFromStore(product.getId());
        Assert.assertEquals(productPage.getProductTitle(),product.getName());

    }

    @Test
    public void navigateFromHomeToFeaturedProductPage() throws IOException {
        Product product = new Product(1215);

        ProductPage productPage =new HomePage(getDriver()).load().navigateToTheProduct(product.getId());

        Assert.assertEquals(productPage.getProductTitle(),product.getName());
    }

}
