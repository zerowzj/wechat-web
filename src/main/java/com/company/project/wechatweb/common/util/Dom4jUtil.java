package com.company.project.wechatweb.common.util;

import com.google.common.collect.Maps;
import com.google.common.io.Closeables;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Map;

/**
 * Dom4j Util
 *
 * @author wangzhj
 */
public abstract class Dom4jUtil {

    public static Document getDoc(String file){
        return getDoc(file, null);
    }

    /**
     * 获取Document
     *
     * @param file
     * @param uri
     * @return List<Element>
     */
    public static Document getDoc(String file, String uri) {
        InputStream is = null;
        Document doc = null;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            is = classLoader.getResourceAsStream(file);

            SAXReader reader = new SAXReader();
            Map<String, String>namespaceURIs = Maps.newHashMap();
            namespaceURIs.put("xmlns", uri);
            reader.getDocumentFactory().setXPathNamespaceURIs(namespaceURIs);

            doc = reader.read(is);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Closeables.closeQuietly(is);
        }
        return doc;
    }

    /**
     * 获取元素属性值
     *
     * @param ele
     * @param attrName
     * @return String
     */
    public static String attrValue(Element ele, String attrName) {
        Attribute attr = ele.attribute(attrName);
        String value = null;
        if (attr != null) {
            value = attr.getValue();
        }
        return value;
    }

    public static String fixedXpath(String xpath) {
        xpath = xpath.replaceAll("/(\\w)", "/" + "xmlns:$1");//replace start with "/"
        xpath = xpath.replaceAll("^(\\w)", "xmlns:$1");    //replace start with word
        return xpath;
    }

}
