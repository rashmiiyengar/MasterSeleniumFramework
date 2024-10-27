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
        System.out.println("result" +result);System.out.println("methods" +methods);

        for (IMethodInstance method : methods) {
            for (Map<String, String> stringStringMap : list) {
                if (method.getMethod().getMethodName().equalsIgnoreCase(stringStringMap.get("testname")) &&
                        stringStringMap.get("execute").equalsIgnoreCase("yes")) {
                    method.getMethod().setDescription((stringStringMap.get("testdescription")));
                    method.getMethod().setInvocationCount(Integer.parseInt(stringStringMap.get("count")));

                    result.add(method);
                }
            }

        }

        return result;
    }


}
