package com.company.project.wechatweb.blogic;

import com.company.project.wechatweb.common.blogic.BaseBLogic;
import com.company.project.wechatweb.msg.MenuMsg;
import com.company.util.JsonUtil;
import org.springframework.stereotype.Component;

/**
 * 自定义
 *
 * @author wangzhj
 */
@Component
public class ClickBLogic extends BaseBLogic<MenuMsg> {

    @Override
    public String createMsg(String openId, MenuMsg msg) {
        LOGGER.info(JsonUtil.toJson(msg));
        return null;
    }

    @Override
    public void processBusiness(String openId, MenuMsg msg) {
    }
}
