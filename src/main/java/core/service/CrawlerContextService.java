package core.service;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * 功能描述：爬取网页内容类
 *
 * @Author: wuyachong
 * @Date: 2020/9/11
 */

public class CrawlerContextService extends WebCrawler {

    private Logger logger = LoggerFactory.getLogger(CrawlerContextService.class);

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz))$");

    /**
     * 这个方法主要是决定哪些url我们需要抓取，返回true表示是我们需要的，返回false表示不是我们需要的Url
     * 第一个参数referringPage封装了当前爬取的页面信息 第二个参数url封装了当前爬取的页面url信息
     * 在这个例子中，我们指定爬虫忽略具有css，js，git，...扩展名的url，只接受以“http://www.ics.uci.edu/”开头的url。
     * 在这种情况下，我们不需要referringPage参数来做出决定。
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches()
                && href.startsWith("https://www.ics.uci.edu/");
    }

    /**
     * 当一个页面被提取并准备好被你的程序处理时，这个函数被调用。
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        logger.info("URL: " + url);

        // 判断是否是html数据
        if (page.getParseData() instanceof HtmlParseData) {
            // 强制类型转换，获取html数据对象
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            // 获取页面纯文本（无html标签）
            String text = htmlParseData.getText();
            // 获取页面Html
            String html = htmlParseData.getHtml();
            // 获取页面输出链接
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            logger.info("Text length: " + text.length());
            logger.info("Html length: " + html.length());
            logger.info("Number of outgoing links: " + links.size());
        }
    }

}
