package com.company.project.wechatweb.auth.filter;

import com.company.project.wechatweb.common.util.WechatConfig;
import com.company.util.HttpWrites;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Api哨兵
 *
 * @author wangzhj
 */
public class ApiGuard extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiGuard.class);

    /**
     * 加密签名参数名
     */
    private static final String SIGNATURE = "signature";
    /**
     * 时间戳参数名
     */
    private static final String TIME_STAMP = "timestamp";
    /**
     * 随机数参数名
     */
    private static final String NONCE = "nonce";
    /**
     * 随机字符串参数名
     */
    private static final String ECHO_STR = "echostr";

    private static final String TOKEN;

    static {
        TOKEN = WechatConfig.getToken();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //有效性验证
        String method = request.getMethod();
        if (Objects.equal("GET", method.toUpperCase()) && isValidity(request)) {
            if (isSignature(request)) {
                HttpWrites.write(response, getEchostr(request));
            } else {
                LOGGER.info("签名验证未通过");
            }
            return;
        }
        //继续执行
        filterChain.doFilter(request, response);
    }

    /**
     * 是否是验证
     */
    private boolean isValidity(HttpServletRequest request) {
        Map<String, Object> params = request.getParameterMap();
        return params.containsKey(SIGNATURE) && params.containsKey(TIME_STAMP) &&
                params.containsKey(NONCE) && params.containsKey(ECHO_STR);
    }

    /**
     * 验证签名
     */
    private boolean isSignature(HttpServletRequest request) {
        String src = Joiner.on("").join(TOKEN, getTimestamp(request), getNonce(request));
        String mySign = DigestUtils.sha1Hex(src);
        if (Objects.equal(mySign, getSignature(request))) {
            return true;
        }
        return false;
    }

    private String getSignature(HttpServletRequest request) {
        return request.getParameter(SIGNATURE);
    }

    private String getTimestamp(HttpServletRequest request) {
        return request.getParameter(TIME_STAMP);
    }

    private String getNonce(HttpServletRequest request) {
        return request.getParameter(NONCE);
    }

    private String getEchostr(HttpServletRequest request) {
        return request.getParameter(ECHO_STR);
    }
}
