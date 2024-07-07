package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    @Test
    public void loginDuringCheckout(){

        SignUpApi signUpApi = new SignUpApi();
        String userName = "demoUser" + new FakerUtils().generateRandomName();

        User user = new User().
                setUsername(userName).
                setPassword(userName).
                setEmail(userName + "@gmail.com");

        signUpApi.register(user);

        CartApi cartApi = new CartApi(); //customer not logged in with empty constructor
        Product product = null;
        try {
            product = new Product(1215);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cartApi.addToCart(product.getId(),1);




    }
}
