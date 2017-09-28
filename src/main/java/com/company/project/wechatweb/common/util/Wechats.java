package com.company.project.wechatweb.common.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * Wechats
 *
 * @author wangzhj
 */
public final class Wechats {

    private static final String FILE = "/wechat.properties";

    private static Properties PROP;

    static {
        try {
            InputStream in = Wechats.class.getResourceAsStream(FILE);
            PROP = new Properties();
            PROP.load(in);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getAppId() {
        return PROP.getProperty("wechat.app_id");
    }

    public static String getSecret() {
        return PROP.getProperty("wechat.secret");
    }

    public static String getToken() {
        return PROP.getProperty("wechat.token");
    }

    public static String getMchId() {
        return PROP.getProperty("wechat.pay.mch_id");
    }
    public static String getKey() {
        return PROP.getProperty("wechat.pay.key");
    }

    public static String getPayNotifyUrl() {
        return PROP.getProperty("wechat.pay.notify_url");
    }

    public static void main(String[] args) {
        System.out.println(getAppId());
    }
}
