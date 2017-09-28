package com.company.project.wechatweb.wechat.msg;

import com.company.project.wechatweb.wechat.token.Tokens;
import com.company.util.JsonUtil;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 自定义消息
 *
 * @author wangzhj
 */
public class CustomMsgs {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomMsgs.class);

    private static final String URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send";

    /**
     * 发送消息
     *
     * @param openId
     * @param content
     */
    public static void sendMsg(String openId, String content) {
        //验证
        Preconditions.checkNotNull(openId);
        Preconditions.checkNotNull(content);
        //参数
        Map<String, Object> data = Maps.newHashMap();
        data.put("touser", openId);
        data.put("msgtype", "text");
        Map<String, String> context = Maps.newHashMap();
        context.put("content", content);
        data.put("text", context);
        //Url
        StringBuffer sb = new StringBuffer(URL);
        sb.append("?access_token=");
        sb.append(Tokens.getAccessToken());
        //请求
        LOGGER.info("发送自定义消息===>{}", JsonUtil.toJson(data));
        HttpRequest request = HttpRequest.post(sb.toString())
                .contentType("application/json", "UTF-8")
                .send(JsonUtil.toJson(data));
        LOGGER.info("code={}", request.code());
        if (request.ok()) {
            LOGGER.info("发送自定义消息<==={}", request.body());
        }
    }
}
