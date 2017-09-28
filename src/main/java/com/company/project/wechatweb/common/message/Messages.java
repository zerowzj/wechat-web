package com.company.project.wechatweb.common.message;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.util.Map;

/**
 * 消息
 *
 * @author wangzhj
 */
public abstract class Messages {

    private static final String DIR = "message/";

    private static GroupTemplate GROUP_TEMPLATE;

    static {
        load();
    }

    private static void load() {
        try {
            ClasspathResourceLoader resLoader = new ClasspathResourceLoader(DIR);
            Configuration cfg = Configuration.defaultConfiguration();
            GROUP_TEMPLATE = new GroupTemplate(resLoader, cfg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 生成信息
     *
     * @param name
     * @param data
     * @return String
     */
    public static String getMessage(String name, Map<String, Object> data) {
        Template template = GROUP_TEMPLATE.getTemplate(name);
        template.binding(data);
        String msg = template.render();
        return msg;
    }
}
