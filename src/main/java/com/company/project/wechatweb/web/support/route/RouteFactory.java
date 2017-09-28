package com.company.project.wechatweb.web.support.route;

import com.company.project.wechatweb.common.util.Dom4jUtil;
import com.google.common.collect.Maps;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.List;
import java.util.Map;

/**
 * 路由工厂
 *
 * @author wangzhj
 */
public class RouteFactory {

    private static final String FILE = "wechat/route.xml";

    private static final String XPATH_NAME_SPACE_URL = "http://www.company.cn/routes";

    private static final String XPATH_ROUTE = "/xmlns:routes/xmlns:route";

    private static final Map<String, String> BEAN_MAP = Maps.newHashMap();

    static {
        Document doc = Dom4jUtil.getDoc(FILE, XPATH_NAME_SPACE_URL);
        List<Element> eleLt = doc.selectNodes(XPATH_ROUTE);
        parse(eleLt);
    }

    private static void parse(List<Element> eleLt) {
        for (Element ele : eleLt) {
            String msgType = Dom4jUtil.attrValue(ele, "msgType");
            String event = Dom4jUtil.attrValue(ele, "event");
            String eventKey = Dom4jUtil.attrValue(ele, "eventKey");

            String key = RouteKeys.keyOfBean(msgType, event, eventKey);
            String bean = Dom4jUtil.attrValue(ele, "bean");

            BEAN_MAP.put(key, bean);
        }
    }

    /**
     * 获取Bean Name
     *
     * @param key
     * @return String
     */
    public static String getBeanName(String key) {
        String beanName = BEAN_MAP.get(key);
        if (beanName == null) {
            throw new IllegalStateException("未获取到key=[" + key + "]的Bean Name");
        }
        return beanName;
    }
}
