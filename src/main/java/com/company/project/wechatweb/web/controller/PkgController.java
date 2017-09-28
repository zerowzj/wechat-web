package com.company.project.wechatweb.web.controller;

import com.company.project.dao.packagebase.PackageBaseEO;
import com.company.project.dao.wechatuserinfo.WechatUserInfoEO;
import com.company.project.service.pkg.PackageService;
import com.company.project.service.wechat.WechatService;
import com.company.project.wechatweb.wechat.auth.AuthTokens;
import com.company.project.wechatweb.auth.OpenIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 包裹控制器
 *
 * @author wangzhj
 */
@Controller
@RequestMapping("/pkg")
public class PkgController {

    @Autowired
    private WechatService wechatService;
    @Autowired
    private PackageService packageService;

    @RequestMapping("/getting_list")
    public ModelAndView getting_list(HttpServletRequest request) {

        String openId = OpenIds.get();
        WechatUserInfoEO wuiEO = wechatService.getByOpenId(openId);
        wuiEO.getWuiUbId();

        String receiverPhone = "";

        List<PackageBaseEO> pbEOLt = packageService.getPackageLt(null, receiverPhone, "W", null, null);

//        String code = request.getParameter("code");
//        try {
//            AuthTokens.getAccessToken(code);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        return new ModelAndView("gettingPkgLt");
    }

    @RequestMapping("/got_list")
    public ModelAndView got_list(HttpServletRequest request) {
        String code = request.getParameter("code");
        try {
            AuthTokens.getAccessToken(code);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ModelAndView("gettingPkgLt");
    }
}
