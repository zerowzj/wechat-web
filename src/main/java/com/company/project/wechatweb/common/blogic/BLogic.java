package com.company.project.wechatweb.common.blogic;

import com.company.project.wechatweb.common.msg.Msg;

/**
 * 业务逻辑
 *
 * @author wangzhj
 */
public interface BLogic<T extends Msg> {

    /**
     * 执行业务
     *
     * @param msg
     */
    void doBusiness(T msg);
}
