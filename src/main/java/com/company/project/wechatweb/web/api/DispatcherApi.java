package com.company.project.wechatweb.web.api;

import com.company.project.wechatweb.common.blogic.BLogic;
import com.company.project.wechatweb.common.msg.Msg;
import com.company.project.wechatweb.common.parser.MsgParser;
import com.company.project.wechatweb.common.util.XmlUtil;
import com.company.project.wechatweb.web.support.route.RouteFactory;
import com.company.project.wechatweb.web.support.route.RouteKeys;
import com.company.util.HttpServlets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 分发Api
 *
 * @author wangzhj
 */
@Controller
@RequestMapping("/api/msg")
public class DispatcherApi implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherApi.class);

    private static ApplicationContext cxt;

    @RequestMapping
    public ResponseEntity<Void> dispatch(HttpServletRequest request) {
        try {
            //请求体
            String xmlBody = HttpServlets.getBodyString(request);
            LOGGER.info("{}", xmlBody);
            //xml ==> map
            Map<String, String> xmlMap = MsgParser.parse(xmlBody);
            //key ==> name ==> bean ==> target
            String key = RouteKeys.keyOfBean(xmlMap);
            String name = RouteFactory.getBeanName(key);
            BLogic bLogic = getBean(name);
            Class clazz = AopUtils.getTargetClass(bLogic);
            //执行业务
            Msg msg = XmlUtil.fromXML(xmlBody, getMsgClazz(clazz));
            bLogic.doBusiness(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 获取处理消息BLogic
     */
    private <T> T getBean(String name) {
        if (!cxt.containsBean(name)) {
            throw new IllegalStateException("未包含[" + name + "]的Bean");
        }
        return (T) cxt.getBean(name);
    }

    /**
     * 获取消息对应Class
     */
    private Class getMsgClazz(Class clazz) {
        if (clazz != null) {
            Type type = clazz.getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                ParameterizedType ptype = ((ParameterizedType) type);
                Type[] args = ptype.getActualTypeArguments();
                clazz = (Class) args[0];
            }
        }
        return clazz;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        cxt = applicationContext;
    }
}
