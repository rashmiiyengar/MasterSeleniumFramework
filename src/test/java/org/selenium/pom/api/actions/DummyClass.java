package org.selenium.pom.api.actions;

import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;

public class DummyClass {

    public static void main(String[] args){

        String userName = "demoUser" + new FakerUtils().generateRandomName();

        User user = new User().
                setUsername(userName).
                setPassword(userName).
                setEmail(userName + "@gmail.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        System.out.println("REGISTER COOKIES" +signUpApi.getCookies() );

        CartApi cartApi = new CartApi(signUpApi.getCookies()); //customer not logged in with empty constructor

        cartApi.addToCart(1215,1);

        System.out.println("CART API COOKIES" +cartApi.getCookies() );

    }

}

