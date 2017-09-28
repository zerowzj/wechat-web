package com.company.project.wechatweb.common.util;

import com.thoughtworks.xstream.XStream;

/**
 * XStream Util
 *
 * @author wangzhj
 */
public abstract class XmlUtil {

    /**
     * xml转object
     *
     * @param xml
     * @return T
     */
    public static <T> T fromXML(String xml, Class<?> clazz) {
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        xstream.alias("xml", clazz);
        return (T) xstream.fromXML(xml);
    }

    /**
     * object转xml
     *
     * @param obj
     * @return String
     */
    public static <T> String toXML(T obj) {
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        return xstream.toXML(obj);
    }
}
