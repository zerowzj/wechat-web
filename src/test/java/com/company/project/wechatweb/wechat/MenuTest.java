package com.company.project.wechatweb.wechat;

import com.company.project.wechatweb.wechat.menu.Menus;
import com.company.project.wechatweb.common.parser.MenuParser;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuTest.class);

    @Test
    public void test() {
        String json = MenuParser.getMenu();
        Menus.initMenu(json);
    }
}
