package org.selenium.pom.listeners;

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

       List<Map<String,String>> list= ExcelUtils.getTestDetails();
        List<IMethodInstance> result = new ArrayList<>();


       for(int i=0;i<methods.size();i++){

           for(int j=0;j<list.size();j++){

               if(methods.get(i).getMethod().getMethodName().equalsIgnoreCase(list.get(j).get("testname"))){
                if(list.get(j).get("execute").equalsIgnoreCase("yes")){

                    methods.get(i).getMethod().setInvocationCount(Integer.parseInt(list.get(j).get("count")));
                    result.add(methods.get(i));
                }

               }


           }
       }

        return List.of();
    }


}
