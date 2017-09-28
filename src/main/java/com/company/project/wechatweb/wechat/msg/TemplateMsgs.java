package com.company.project.wechatweb.wechat.msg;

import com.company.project.wechatweb.wechat.token.Tokens;
import com.company.util.JsonUtil;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 模板消息
 *
 * @author wangzhj
 */
public class TemplateMsgs {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateMsgs.class);

    private static final String URL = "https://api.weixin.qq.com/cgi-bin/message/template/send";

    /**
     * 发送消息
     *
     * @param openId
     * @param templateId
     * @param templateId
     */
    public static void sendMsg(String openId, String templateId, List data) {
        //验证
        Preconditions.checkNotNull(openId);
        Preconditions.checkNotNull(templateId);
        Preconditions.checkNotNull(data);
        //参数
        Map<String, Object> body = Maps.newHashMap();
        body.put("touser", openId);
        body.put("template_id", templateId);
        Map<String, String> context = Maps.newHashMap();
        body.put("data", context);
        //Url
        StringBuffer sb = new StringBuffer(URL);
        sb.append("?access_token=");
        sb.append(Tokens.getAccessToken());
        //请求
        LOGGER.info(JsonUtil.toJson(data));
        HttpRequest request = HttpRequest.post(sb.toString())
                .contentType("application/json", "UTF-8")
                .send(JsonUtil.toJson(data));
        LOGGER.info("code={}", request.code());
        if (request.ok()) {
            LOGGER.info(request.body());
        }
    }
}
