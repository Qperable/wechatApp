package booter.wechatapp.core.util;

import booter.wechatapp.core.bean.ContextParseBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
     * 咪咕音乐报文解析方法
     * @param context
     * @return
     */
    public static ContextParseBean transMiGuContextTobean(String context) {
        ContextParseBean bean = new ContextParseBean();
        Document doc = null;
        try {
            doc = Jsoup.parse(context);
            Elements aTag = doc.getElementsByTag("a");
            String aClass = aTag.attr("href");
            String aText = aTag.text();
            Map<String, String> classMap = new HashMap<>();
            classMap.put("href", aClass);
            bean.setTag("a");
            bean.setContext(aText);
            bean.setClassMap(classMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
