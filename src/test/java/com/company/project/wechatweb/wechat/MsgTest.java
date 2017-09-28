package com.company.project.wechatweb.wechat;

import com.company.project.wechatweb.wechat.msg.CustomMsgs;
import com.company.project.wechatweb.wechat.msg.TemplateMsgs;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MsgTest.class);

    private static final String openId = "oeAbiwxhpKI3NHeE3rJOywaLsN1g";

    @Test
    public void test＿custom() {
        String content = "这是一条自定义消息";
        CustomMsgs.sendMsg(openId, content);
    }

    @Test
    public void test＿template() {
        String templateId = "12321312";
        TemplateMsgs.sendMsg(openId, templateId, null);
    }
}
