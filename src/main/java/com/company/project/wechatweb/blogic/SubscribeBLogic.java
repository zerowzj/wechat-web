package com.company.project.wechatweb.blogic;

import com.company.project.dao.wechatuserinfo.WechatUserInfoEO;
import com.company.project.service.wechat.WechatService;
import com.company.project.wechatweb.wechat.userinfo.UserInfo;
import com.company.project.wechatweb.wechat.userinfo.UserInfos;
import com.company.project.wechatweb.common.blogic.BaseBLogic;
import com.company.project.wechatweb.msg.SubscribeMsg;
import com.company.util.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 关注
 *
 * @author wangzhj
 */
@Component
public class SubscribeBLogic extends BaseBLogic<SubscribeMsg> {

    @Autowired
    private WechatService wechatService;

    @Override
    public String createMsg(String openId, SubscribeMsg msg) {
        LOGGER.info("[{}]关注公众号", openId);
        return "欢迎你关注了公众号";
    }

    @Override
    public void processBusiness(String openId, SubscribeMsg msg) {
        //
        UserInfo userInfo = UserInfos.getUserInfo(openId);
        //
        WechatUserInfoEO wuiEO = wechatService.getByOpenId(openId);
        if(wuiEO == null){
            wuiEO = new WechatUserInfoEO();
            wuiEO.setWuiOpenId(openId);
            wuiEO.setWuiNickName(userInfo.getNickname());
            wuiEO.setWuiCountry(userInfo.getCountry());
            wuiEO.setWuiProvince(userInfo.getProvince());
            wuiEO.setWuiCity(userInfo.getCity());
            wuiEO.setWuiHeadImgUrl(userInfo.getHeadimgurl());
            wuiEO.setWuiBeginTime(Dates.now());

            wechatService.addWechatUser(wuiEO);
        } else {
            wuiEO.setWuiOpenId(openId);
            wuiEO.setWuiNickName(userInfo.getNickname());
            wuiEO.setWuiCountry(userInfo.getCountry());
            wuiEO.setWuiProvince(userInfo.getProvince());
            wuiEO.setWuiHeadImgUrl(userInfo.getHeadimgurl());
            wuiEO.setWuiSubStatus("Y");
            wuiEO.setWuiBeginTime(Dates.now());
            wuiEO.setWuiEndTime(null);

            wechatService.modifyWechatUser(wuiEO);
        }
    }
}
