package com.daniel.httputils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Set;

/**
 * Created by danielchang on 2018/8/19.
 */
//@Component
public class SpringMvcInitUtil implements ServletContextListener {
    @Resource
    private RequestMappingHandlerMapping handlerMapping;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("============================listen");

        Set<RequestMappingInfo> rmSet = handlerMapping.getHandlerMethods().keySet();
        for (RequestMappingInfo rm : rmSet) {
            System.out.println(rm.getPatternsCondition().toString());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
