package com.company.project.wechatweb.wechat.token;

import com.company.project.wechatweb.common.util.WechatConfig;
import com.company.util.JsonUtil;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Tokens
 *
 * @author wangzhj
 */
public class Tokens {

    private static final Logger LOGGER = LoggerFactory.getLogger(Tokens.class);

    private static final String URL = "https://api.weixin.qq.com/cgi-bin/token";

    private static final String APP_ID;

    private static final String SECRET;

    private static String accessToken;

    private static long overTimeMillis = -1;

    static {
        APP_ID = WechatConfig.getAppId();
        SECRET = WechatConfig.getSecret();
    }

    /**
     * 获取Access Token
     *
     * @return String
     */
    public synchronized static String getAccessToken() {
        long now = System.currentTimeMillis();
        if (overTimeMillis <= now) {
            doGet();
        }
        if (Strings.isNullOrEmpty(accessToken)) {
            throw new IllegalStateException("未获取到[access token]");
        }
        return accessToken;
    }

    private synchronized static void doGet() {
        //生成参数
        Map<String, String> params = Maps.newHashMap();
        params.put("grant_type", "client_credential");
        params.put("appid", APP_ID);
        params.put("secret", SECRET);
        //
        LOGGER.info("获取Access Token==>{}", JsonUtil.toJson(params));
        HttpRequest request = HttpRequest.get(URL, params, false);
        Token token = null;
        if (request.ok()) {
            String body = request.body();
            LOGGER.info("获取Access Token<==={}", body);
            token = JsonUtil.fromJson(body, Token.class);
        }
        if (token != null && token.ok()) {
            overTimeMillis = System.currentTimeMillis() + token.getExpires_in() * 1000;
            accessToken = token.getAccess_token();
        }
    }
}
