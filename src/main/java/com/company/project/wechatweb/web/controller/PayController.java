package com.company.project.wechatweb.web.controller;

import com.company.project.service.pkg.PackageService;
import com.company.project.wechatweb.wechat.auth.AuthTokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 支付控制器
 *
 * @author wangzhj
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PackageService packageService;

    @RequestMapping("/order")
    public void order(HttpServletRequest request) {

        String code = request.getParameter("code");
        try {
            AuthTokens.getAccessToken(code);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
