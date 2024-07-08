package org.selenium.pom.dataproviders;

import org.selenium.pom.objects.Product;
import org.selenium.pom.utils.JacksonUtil;

import java.io.IOException;

public class MyDataProvider {

    @org.testng.annotations.DataProvider(name = "getFeaturedProducts",parallel = true)
    public Product[]  getFeaturedProducts() throws IOException {

        return JacksonUtil.deserializeJson("products.json",Product[].class);

    }
}
