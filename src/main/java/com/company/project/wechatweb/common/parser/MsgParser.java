package com.company.project.wechatweb.common.parser;

import com.google.common.collect.Maps;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * 微信消息解析器
 *
 * @author wangzhj
 */
public class MsgParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(MsgParser.class);

    /**
     * 解析
     *
     * @param xml
     * @return Map<String, String>
     */
    public static Map<String, String> parse(String xml) {
        Map<String, String> data = Maps.newHashMap();
        SAXReader reader = new SAXReader();
        Document doc;
        try {
            Reader in = CharSource.wrap(xml).openStream();
            doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> eleLt = root.elements();
            for (Element ele : eleLt) {
                String key = ele.getName();
                String value = ele.getText();

//                LOGGER.info("{} = {}", key, value);

                data.put(key, value);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args)throws Exception {
        String path = "D:\\project\\my\\wechat-web\\src\\main\\resources\\msg.xml";
        ByteSource byteSource = Files.asByteSource(new File(path));

        Map<String, String> d = parse(byteSource.asCharSource(Charset.defaultCharset()).read());

        System.out.println(d);
    }
}
