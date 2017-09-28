package com.company.project.wechatweb.common.blogic;

import com.company.project.wechatweb.wechat.msg.CustomMsgs;
import com.company.project.wechatweb.common.msg.Msg;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * 基本业务逻辑
 *
 * @author wangzhj
 */
public abstract class BaseBLogic<T extends Msg> implements BLogic<T> {

    protected static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public final void doBusiness(T msg) {
        try {
            //获取OpenId
            String openId = msg.getFromUserName();
            //发送消息
            String content = createMsg(openId, msg);
            if (!Strings.isNullOrEmpty(content)) {
                CustomMsgs.sendMsg(msg.getFromUserName(), content);
            }
            //处理业务
            processBusiness(openId, msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 生成消息
     *
     * @param msg
     */
    public abstract String createMsg(String openId, T msg);

    /**
     * 处理业务
     *
     * @param msg
     */
    public abstract void processBusiness(String openId, T msg);
}
