package com.daniel.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by danielchang on 2018/6/23.
 */
public class LocalServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("session 创建 " + sce.getServletContext().getContextPath());
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("session 销毁");
    }
}
