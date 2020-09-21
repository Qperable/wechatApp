package core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 功能描述：爬取网页内容匹配类
 *
 * @Author: wuyachong
 * @Date: 2020/9/11
 */

public class CrawlerContextService implements PageProcessor {

    private Logger logger = LoggerFactory.getLogger(CrawlerContextService.class);

    private Site site = Site.me().setDomain("127.0.0.1");

    @Override
    public void process(Page page) {
        page.putField("title", page.getHtml().xpath("//div[@class='song-name']|//div[@class='song-singer']").all().toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
