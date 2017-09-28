package com.company.project.wechatweb.blogic;

import com.company.project.dao.wechatuserinfo.WechatUserInfoEO;
import com.company.project.service.wechat.WechatService;
import com.company.project.wechatweb.common.blogic.BaseBLogic;
import com.company.project.wechatweb.msg.SubscribeMsg;
import com.company.util.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消关注
 *
 * @author wangzhj
 */
@Component
public class UnsubscribeBLogic extends BaseBLogic<SubscribeMsg> {

    @Autowired
    private WechatService wechatService;

    @Override
    public String createMsg(String openId, SubscribeMsg msg) {
        LOGGER.info("[{}]取消关注公众号", openId);
        return null;
    }

    @Override
    public void processBusiness(String openId, SubscribeMsg msg) {
        WechatUserInfoEO wuiEO = wechatService.getByOpenId(openId);
        if (wuiEO == null) {
            wuiEO.setWuiSubStatus("N");
            wuiEO.setWuiEndTime(Dates.now());
            wechatService.modifyWechatUser(wuiEO);
        }
    }
}
