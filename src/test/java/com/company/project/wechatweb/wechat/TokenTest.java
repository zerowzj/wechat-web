package com.company.project.wechatweb.wechat;

import com.company.project.wechatweb.wechat.token.Tokens;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenTest.class);

    @Test
    public void test_token(){
        LOGGER.info(Tokens.getAccessToken());
    }

    @Test
    public void test_authToken(){
        LOGGER.info(Tokens.getAccessToken());
    }
}
