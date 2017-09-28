package com.company.project.wechatweb.common.parser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.List;

/**
 * 按钮
 *
 * @author wangzhj
 */
@JsonInclude(Include.NON_NULL)
public class Button implements Serializable {

    //按钮名称
    private String name;
    ///按钮类型
    private String type;
    //点击key
    private String key;
    //视图url
    private String url;
    //子按钮列表
    private List<Button> sub_button;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Button> sub_button) {
        this.sub_button = sub_button;
    }
}
