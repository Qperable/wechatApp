package core.config;

/**
 * 功能描述：配置文件初始化类
 *
 * @Author: wuyachong
 * @Date: 2020/10/19
 */
public class ConfigIniter {

    private static CrawlerConfig crawlerConfig;

    public static CrawlerConfig initCrawler() {
        if (crawlerConfig == null) {
            crawlerConfig = new CrawlerConfig();
        }
        return crawlerConfig;
    }
}
