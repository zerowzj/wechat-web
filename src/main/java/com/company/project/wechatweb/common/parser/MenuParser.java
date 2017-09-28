package com.company.project.wechatweb.common.parser;

import com.company.project.wechatweb.common.util.Dom4jUtil;
import com.company.util.JsonUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 微信菜单解析器
 *
 * @author wangzhj
 */
public class MenuParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuParser.class);

    private static final String FILE = "wechat/menu.xml";

    private static final String XPATH_BUTTON = "/xmlns:menu/xmlns:button";

    private static final String XPATH_NAME_SPACE_URL = "http://www.company.cn/menu";

    private static List<Button> BTN_LT = Lists.newArrayList();

    static {
        Document doc = Dom4jUtil.getDoc(FILE, XPATH_NAME_SPACE_URL);
        //menu --> button
        List<Element> eleLt = doc.selectNodes(XPATH_BUTTON);
        parse_button(eleLt);
    }

    /**
     * 解析menu下的button
     *
     * @param btnEleLt
     */
    private static void parse_button(List<Element> btnEleLt) {
        for (Element btnEle : btnEleLt) {
            Button btn = new Button();
            btn.setName(Dom4jUtil.attrValue(btnEle, "name"));
            String type = Dom4jUtil.attrValue(btnEle, "type");
            if ("view".equals(type) || "click".equals(type)) {
                btn = parse(btnEle);
            } else {
                List<Element> subBtnEleLt = btnEle.elements("sub_btn");
                List<Button> subBtnLt = Lists.newArrayList();
                for (Element suBtnEle : subBtnEleLt) {
                    subBtnLt.add(parse(suBtnEle));
                }
                btn.setSub_button(subBtnLt);
            }
            BTN_LT.add(btn);
        }
    }

    public static Button parse(Element btnEle) {
        Button btn = new Button();
        btn.setName(Dom4jUtil.attrValue(btnEle, "name"));
        String type = Dom4jUtil.attrValue(btnEle, "type");
        btn.setType(type);
        if("view".equals(type)){
            Element urlEle = btnEle.element("url");
            btn.setUrl(urlEle.getTextTrim());
        } else if("click".equals(type)){
            Element keyEle = btnEle.element("key");
            btn.setKey(keyEle.getTextTrim());
        }
        return btn;
    }

    /**
     * 获取菜单
     *
     * @return String
     */
    public static String getMenu() {
        Map<String, Object> data = Maps.newHashMap();
        data.put("button", BTN_LT);
        return JsonUtil.toJson(data);
    }

    public static void main(String[] args) {
        LOGGER.info(getMenu());
    }
}
