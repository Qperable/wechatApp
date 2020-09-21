package core.interfaces;

/**
 * 功能描述：
 *
 * @Author: wuyachong
 * @Date: 2020/9/19
 */

public interface CrawlerUseService {

    /**
     * 咪咕音乐爬取
     * 优先从本地读取数据，如本地数据不存在，则通过url爬取
     * @param url
     */
    String crawMiGuMusic(String url);

}
