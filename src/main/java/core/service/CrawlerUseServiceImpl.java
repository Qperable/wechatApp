package core.service;

import core.interfaces.CrawlerUseService;
import core.properties.CrawlerProperties;
import core.util.FileHandleUtils;
import core.util.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

/**
 * 功能描述：爬虫使用工具类
 *
 * @Author: wuyachong
 * @Date: 2020/9/11
 */

@Service
@EnableConfigurationProperties(CrawlerProperties.class)
public class CrawlerUseServiceImpl implements CrawlerUseService {

    @Resource
    private CrawlerProperties crawlerProperties;

    /**
     * 咪咕音乐爬取
     * 优先从本地读取数据，如本地数据不存在，则通过url爬取
     * @param url
     */
    @Override
    public String crawMiGuMusic(String url) {
        String fileName = FileHandleUtils.assembleTextName(crawlerProperties.getLocal(), url);
        String context = FileHandleUtils.fileReadByMap(fileName);

        if (StringUtils.isEmpty(context)) {
            // 如果文件不存在，则从链接爬取内容下载到本地
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
