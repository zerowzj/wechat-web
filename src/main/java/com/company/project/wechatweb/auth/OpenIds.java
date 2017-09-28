package com.company.project.wechatweb.auth;

/**
 * 微信OpenId
 *
 * @author wangzhj
 */
public class OpenIds {

    private static ThreadLocal<String> LOCAL = new ThreadLocal<>();

    public static void set(String openId) {
        LOCAL.set(openId);
    }

    public static String get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }
}
