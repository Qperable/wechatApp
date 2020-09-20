package booter.wechatapp.core.enums;

import booter.wechatapp.core.constants.CrawlerMethodConstant;
import booter.wechatapp.core.constants.CrawlerUrlConstant;

/**
 * 功能描述：链接枚举
 *
 * @Author: wuyachong
 * @Date: 2020/9/19
 */

public enum UrlEnum {

    MI_GU_TOP_NEW_SONG(CrawlerUrlConstant.MI_GU_TOP_NEW_SONG_URL, CrawlerMethodConstant.MI_GU_TOP_NEW_SONG_METHOD, "咪咕音乐尖叫新歌榜");

    public static String getCnNameByUrl(String url) {
        for (UrlEnum value : values()) {
            if (value.url.equals(url)) {
                return value.cnName;
            }
        }
        return null;
    }

    public static String getMethodByUrl(String url) {
        for (UrlEnum value : values()) {
            if (value.url.equals(url)) {
                return value.crawMethod;
            }
        }
        return null;
    }

    UrlEnum(String url, String crawMethod, String cnName) {
        this.url = url;
        this.crawMethod = crawMethod;
        this.cnName = cnName;
    }

    private String url;
    private String crawMethod;
    private String cnName;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCrawMethod() {
        return crawMethod;
    }

    public void setCrawMethod(String crawMethod) {
        this.crawMethod = crawMethod;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}
