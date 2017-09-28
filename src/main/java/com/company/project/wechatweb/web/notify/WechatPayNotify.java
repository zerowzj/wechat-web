package com.company.project.wechatweb.web.notify;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 微信支付通知
 *
 * @author wangzhj
 */
@Controller
@RequestMapping(value = "/pay/notify_by_wechat",
        consumes = MediaType.APPLICATION_XML_VALUE,
        produces = MediaType.APPLICATION_XML_VALUE)
public class WechatPayNotify {

    @RequestMapping
    @ResponseBody
    public NotifyResult notify(@RequestBody NotifyParam param) {
        //
        NotifyResult result = new NotifyResult();
        result.setReturn_code("SUCCESS");
        result.setReturn_msg("OK");
        return result;
    }
}
