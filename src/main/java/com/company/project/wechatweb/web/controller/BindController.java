package com.company.project.wechatweb.web.controller;

import com.company.project.service.pkg.PackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 绑定控制器
 *
 * @author wangzhj
 */
@Controller
@RequestMapping("/bind")
public class BindController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BindController.class);

    @Autowired
    private PackageService packageService;

    @RequestMapping("/reg")
    public ModelAndView reg(HttpServletRequest request) {
        LOGGER.info("sssssssss");
//        String code = request.getParameter("code");
//        try {
//            AuthTokens.getAccessToken(code);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        return new ModelAndView("gettingPkgLt");
    }

    @RequestMapping("/associate")
    public ModelAndView associate(HttpServletRequest request) {

        LOGGER.info("sssssssss");
//        String code = request.getParameter("code");
//        try {
//            AuthTokens.getAccessToken(code);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        return new ModelAndView("gettingPkgLt");
    }
}
