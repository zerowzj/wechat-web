package com.company.project.wechatweb.web.support.listener;

import com.company.project.wechatweb.wechat.menu.Menus;
import com.company.project.wechatweb.common.parser.MenuParser;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 资源加载
 *
 * @author wangzhj
 */
public class ResLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String json = MenuParser.getMenu();
        Menus.initMenu(json);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
