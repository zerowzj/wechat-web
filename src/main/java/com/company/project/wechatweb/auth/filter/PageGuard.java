package com.company.project.wechatweb.auth.filter;

import com.company.project.dao.wechatuserinfo.WechatUserInfoEO;
import com.company.project.service.wechat.WechatService;
import com.company.project.wechatweb.wechat.auth.AuthToken;
import com.company.project.wechatweb.wechat.auth.AuthTokens;
import com.company.project.wechatweb.auth.OpenIds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 页面哨兵
 *
 * @author wangzhj
 */
public class PageGuard extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageGuard.class);

    private static final String REG_PAGE = "/view/bind.jsp";

    @Autowired
    private WechatService wechatService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        LOGGER.info("QueryString===>{}", request.getQueryString());
        try {
            //获取OpenId
            String code = request.getParameter("code");
            AuthToken token = AuthTokens.getAccessToken(code);
            String openId = token.getOpenid();
            OpenIds.set(openId);
            //获取用户微信信息
            WechatUserInfoEO wuiEO = wechatService.getByOpenId(openId);
            if (wuiEO == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(REG_PAGE);
                request.setAttribute("openId", openId);
                dispatcher.forward(request, response);
                return;
            }
            //继续执行
            filterChain.doFilter(request, response);
        } finally {
            OpenIds.remove();
        }
    }
}
