package com.company.project.wechatweb.wechat.auth;

import com.company.project.wechatweb.common.util.WechatConfig;
import com.company.util.JsonUtil;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 认证Token
 *
 * @author wangzhj
 */
public class AuthTokens {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokens.class);

    private static final String URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    private static final String APP_ID;

    private static final String SECRET;

    private static AuthToken token;

    private static long overTimeMillis = -1;

    static {
        APP_ID = WechatConfig.getAppId();
        SECRET = WechatConfig.getSecret();
    }

    /**
     * 获取access_token
     *
     * @param code
     * @return String
     */
    public static AuthToken getAccessToken(String code) {
        long now = System.currentTimeMillis();
        if (overTimeMillis <= now) {
            LOGGER.info("请求获取[access_token]");
            doGetToken(code);
        }
        if (token == null) {
            throw new IllegalStateException("未获取到[access_token]");
        }
        return token;
    }

    private static void doGetToken(String code) {
        Map<String, String> params = Maps.newHashMap();
        params.put("grant_type", "authorization_code");
        params.put("appid", APP_ID);
        params.put("secret", SECRET);
        params.put("code", code);
        HttpRequest request = HttpRequest.get(URL, params, false);


        if (request.ok()) {
            String body = request.body();
            LOGGER.info(body);
            token = JsonUtil.fromJson(body, AuthToken.class);
        }
        if (token != null && token.ok()) {
            overTimeMillis = System.currentTimeMillis() + token.getExpires_in() * 1000;
        }
    }
}
