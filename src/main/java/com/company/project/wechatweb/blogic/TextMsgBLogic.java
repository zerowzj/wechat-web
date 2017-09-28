package com.company.project.wechatweb.blogic;

import com.company.project.wechatweb.common.blogic.BaseBLogic;
import com.company.project.wechatweb.msg.TextMsg;
import org.springframework.stereotype.Component;

/**
 * 文本消息
 *
 * @author wangzhj
 */
@Component
public class TextMsgBLogic extends BaseBLogic<TextMsg> {

    @Override
    public String createMsg(String openId, TextMsg msg) {
        LOGGER.info("[{}]发送文本消息[{}]", openId, msg.getContent());
        return "你发的是: "+ msg.getContent();
    }

    @Override
    public void processBusiness(String openId, TextMsg msg) {

    }
}
