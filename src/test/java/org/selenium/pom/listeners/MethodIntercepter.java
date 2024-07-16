package org.selenium.pom.listeners;

import org.selenium.pom.constants.FrameworkConstants;
import org.selenium.pom.utils.ExcelUtils;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MethodIntercepter  implements IMethodInterceptor {


    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext iTestContext) {
        System.out.println("Intercepting methods...");
       List<Map<String,String>> list= ExcelUtils.getTestDetails(FrameworkConstants.getRunmangersheet());
        System.out.println("Test details from Excel: " + list);
        List<IMethodInstance> result = new ArrayList<>();

        for (IMethodInstance method : methods) {

            for (Map<String, String> stringStringMap : list) {

                String testName = stringStringMap.get("testname");
                String execute = stringStringMap.get("execute");
                String count = stringStringMap.get("count");

                System.out.println(testName);
                System.out.println(execute);
                System.out.println(count);
                if (testName != null && execute != null && count != null  && method.getMethod().getMethodName().equalsIgnoreCase(testName)) {
                    if (stringStringMap.get("execute").equalsIgnoreCase("yes")) {
                        method.getMethod().setInvocationCount(Integer.parseInt(count));
                        result.add(method);
                    }
                }
            }
        }

        return result;
    }


}
