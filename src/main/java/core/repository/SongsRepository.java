package core.repository;

import core.bean.SongsBean;
import core.config.ConfigIniter;
import core.service.CrawlerContextService;
import core.service.CrawlerPipelineService;
import core.util.FileHandleUtils;
import core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：音乐类查询方法
 *
 * @Author: wuyachong
 * @Date: 2020/10/28
 */

@Service
public class SongsRepository {

    private static Logger logger = LoggerFactory.getLogger(SongsRepository.class);

    public List<SongsBean> selectSingsByUrl(String url) {
        // 生成本地文件名
        String fileName = FileHandleUtils.assembleTextName(ConfigIniter.initCrawler().getStaticLocal(), url);
        // 从本地文件读取缓存信息
        String context = FileHandleUtils.fileReadByMap(url);

        if (StringUtils.isEmpty(context)) {
            // 如果文件不存在，则从链接爬取内容下载到本地
            logger.info("文件不存在，网络下载中...");
            Spider.create(new CrawlerContextService()).addUrl(url)
                    .addPipeline(new CrawlerPipelineService()).thread(1).run();
            context = FileHandleUtils.fileReadByMap(fileName);
            if (StringUtils.isEmpty(context)) {
                return null;
            }
        }

        String[] split;
        List<SongsBean> songsBeanList = new ArrayList<>();
        SongsBean songsBean;
        String[] songAndSingerList = context.split("\n");
        // 遍历所有歌曲名及歌手
        for (String bean : songAndSingerList) {
            songsBean = new SongsBean();
            split = bean.split(":");
            songsBean.setSongName(split[0]);
            songsBean.setSingerName(split[1]);
            songsBeanList.add(songsBean);
        }

        return songsBeanList;
    }
}
