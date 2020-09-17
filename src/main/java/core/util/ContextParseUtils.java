package core.util;

import core.bean.ContextParseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：报文解析工具
 *
 * @Author: wuyachong
 * @Date: 2020/9/17
 */

public class ContextParseUtils {

    /**
     * 转换双标签内容
     * @param context
     * @return
     */
    public static ContextParseBean transDoubleContextTobean(String context) {
        String tagContext = context.substring(0, context.indexOf(">"));
        String beanContext = context.substring(context.indexOf(">") + 1, context.lastIndexOf("<"));
        String[] tags = tagContext.split(" ");
        Map<String, String> classMap = new HashMap<>();
        // TODO: 2020/9/18 这里如果分出来的tags只有一个的话可以考虑抛出自定义异常，让框架捕获该异常并返回固定值
        String[] map;
        String key;
        String value;
        for (String tag : tags) {
            map = tag.split("=");
            if (map.length > 1) {
                key = map[0];
                value = map[1];
                // 去掉双引号
                value = value.substring(1, value.length()-1);
                classMap.put(key, value);
            }
        }
        ContextParseBean bean = new ContextParseBean();
        bean.setTag(tags[0].substring(1));
        bean.setContext(beanContext);
        bean.setClassMap(classMap);
        return bean;
    }
}
