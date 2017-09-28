package com.company.project.wechatweb.auth.extend;

import com.company.util.HttpServlets;
import com.google.common.base.Strings;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by wangzhj on 2017/4/22.
 */
public class RepeatHttpRequest extends HttpServletRequestWrapper {

    /**
     * 请求体
     */
    private byte[] body = new byte[]{};

    public RepeatHttpRequest(HttpServletRequest request) {
        super(request);
        //可重复读
        String bodyStr = HttpServlets.getBodyString(request);
        if(!Strings.isNullOrEmpty(bodyStr)){
            body = bodyStr.getBytes(Charset.forName("UTF-8"));
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream baIs = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return baIs.read();
            }
        };
    }
}
