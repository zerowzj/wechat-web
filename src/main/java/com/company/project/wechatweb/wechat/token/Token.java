package com.company.project.wechatweb.wechat.token;

import com.company.project.wechatweb.wechat.Resp;

/**
 * Created by wangzhj on 2017/6/6.
 */
public class Token extends Resp {

    //Token
    private String access_token;
    //有效时间(秒)
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
