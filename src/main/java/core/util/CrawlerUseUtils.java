package core.util;

import core.service.CrawlerContextService;
import core.service.CrawlerPipelineService;
import us.codecraft.webmagic.Spider;

/**
 * 功能描述：爬虫使用工具类
 *
 * @Author: wuyachong
 * @Date: 2020/9/11
 */

public class CrawlerUseUtils {

    public static void main(String[] args) {
        //获取影片标题和页面链接
        Spider.create(new CrawlerContextService()).addUrl("https://music.migu.cn/v3/music/top/jianjiao_newsong")
                .addPipeline(new CrawlerPipelineService()).thread(1).run();
    }

}
