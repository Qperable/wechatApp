package core.service;

import core.config.ConfigIniter;
import core.interfaces.CrawlerUseService;
import core.util.FileHandleUtils;
import core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

/**
 * 功能描述：爬虫使用工具类
 *
 * @Author: wuyachong
 * @Date: 2020/9/11
 */

@Service
public class CrawlerUseServiceImpl implements CrawlerUseService {

    private static Logger logger = LoggerFactory.getLogger(CrawlerUseServiceImpl.class);

    /*@Resource
    private CrawlerConfig crawlerConfig;*/

    /**
     * 咪咕音乐爬取
     * 优先从本地读取数据，如本地数据不存在，则通过url爬取
     * @param url
     */
    @Override
    public String crawMiGuMusic(String url) {
        String fileName = FileHandleUtils.assembleTextName(ConfigIniter.initCrawler().getStaticLocal(), url);
        logger.info("开始读取已有文件内容...");
        String context = FileHandleUtils.fileReadByMap(fileName);

        if (StringUtils.isEmpty(context)) {
            // 如果文件不存在，则从链接爬取内容下载到本地
            logger.info("文件不存在，网络下载中...");
            Spider.create(new CrawlerContextService()).addUrl(url)
                    .addPipeline(new CrawlerPipelineService()).thread(1).run();
            context = FileHandleUtils.fileReadByMap(fileName);
            if (StringUtils.isEmpty(context)) {
                return "该功能可能存在某种异常，请稍后再试";
            }
        }

        return context;
    }

}
