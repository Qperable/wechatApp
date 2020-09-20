package booter.wechatapp.core.service;

import booter.wechatapp.core.interfaces.CrawlerUseService;
import booter.wechatapp.core.util.FileHandleUtils;
import booter.wechatapp.core.util.StringUtils;
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

    /**
     * 咪咕音乐爬取
     * 优先从本地读取数据，如本地数据不存在，则通过url爬取
     * @param url
     */
    @Override
    public String crawMiGuMusic(String url) {
        String fileName = ""; // FileHandleUtils.assembleTextName(url);
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
