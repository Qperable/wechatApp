package booter.wechatapp.core.bean;

import java.util.Map;

/**
 * 功能描述：报文解析类
 *
 * @Author: wuyachong
 * @Date: 2020/9/17
 */

public class ContextParseBean {

    /**
     * 标签
     */
    private String tag;

    /**
     * 内容
     */
    private String context;

    /**
     * 标签属性，如href, class
     */
    private Map<String, String> classMap;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Map<String, String> getClassMap() {
        return classMap;
    }

    public void setClassMap(Map<String, String> classMap) {
        this.classMap = classMap;
    }
}
