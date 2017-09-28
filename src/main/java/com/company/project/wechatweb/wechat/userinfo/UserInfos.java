package com.company.project.wechatweb.wechat.userinfo;

import com.company.project.wechatweb.wechat.token.Tokens;
import com.company.util.JsonUtil;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 用户信息
 *
 * @author wangzhj
 */
public class UserInfos {

    private static final Logger LOGGER = LoggerFactory.getLogger(Tokens.class);

    private static final String URL = "https://api.weixin.qq.com/cgi-bin/user/info";

    private static final String LANG = "zh_CN";

    /**
     * 获取用户信息
     *
     * @param openId
     * @return UserInfo
     */
    public static UserInfo getUserInfo(String openId) {
        //验证
        Preconditions.checkNotNull(openId);
        //参数
        Map<String, String> params = Maps.newHashMap();
        params.put("access_token", Tokens.getAccessToken());
        params.put("openid", openId);
        params.put("lang", LANG);
        //请求
        HttpRequest request = HttpRequest.post(URL, params, false);
        UserInfo userInfo = null;
        if (request.ok()) {
            String body = request.body();
            LOGGER.info("{}", body);
            userInfo = JsonUtil.fromJson(body, UserInfo.class);
        }
        return userInfo;
    }
}

