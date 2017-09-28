package com.company.project.wechatweb.wechat;

import com.company.project.wechatweb.wechat.userinfo.UserInfo;
import com.company.project.wechatweb.wechat.userinfo.UserInfos;
import com.company.util.JsonUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserInfoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoTest.class);

    private static final String openId = "oeAbiwxhpKI3NHeE3rJOywaLsN1g";

    @Test
    public void test() {
        UserInfo userInfo = UserInfos.getUserInfo(openId);
        LOGGER.info(JsonUtil.toJson(userInfo));
    }
}
