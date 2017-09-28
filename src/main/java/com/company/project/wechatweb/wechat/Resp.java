package com.company.project.wechatweb.wechat;

import java.io.Serializable;

/**
 * 响应结果
 *
 * @author wangzhj
 */
public class Resp implements Serializable {

    //错误码
    private String errcode = "0";
    //错误描述
    private String errmsg;

    public boolean ok() {
        return errcode != null && "0".equals(errcode);
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
