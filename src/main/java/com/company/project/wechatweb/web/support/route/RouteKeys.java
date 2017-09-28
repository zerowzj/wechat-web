package com.company.project.wechatweb.web.support.route;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import java.util.Map;

/**
 * 路由Key
 *
 * @author wangzhj
 */
public class RouteKeys {

    private static final String MSG_TYPE = "MsgType";

    private static final String EVENT = "Event";

    private static final String EVENT_KEY = "EventKey";

    private static final char SEPARATOR = '_';

    /**
     * 获取Bean Key
     *
     * @param msgType
     * @param event
     * @param eventKey
     * @return String
     */
    public static String keyOfBean(String msgType, String event, String eventKey) {
        Joiner joiner = Joiner.on(SEPARATOR).skipNulls();
        String key;
        if ("VIEW".equals(event)) {
            key = joiner.join(msgType, event);
        } else {
            key = joiner.join(msgType, event, Strings.emptyToNull(eventKey));
        }
        return key;
    }

    /**
     * 获取Bean Key
     *
     * @param xmlMap
     * @return String
     */
    public static String keyOfBean(Map<String, String> xmlMap) {
        String msgType = xmlMap.get(MSG_TYPE);
        String event = xmlMap.get(EVENT);
        String eventKey = xmlMap.get(EVENT_KEY);
        return keyOfBean(msgType, event, eventKey);
    }
}
