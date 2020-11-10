package core.service;

import core.bean.SongsBean;
import core.interfaces.CrawlerUseService;
import core.repostory.SongsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private SongsRepository songsRepository;

    /**
     * 咪咕音乐爬取
     * 优先从本地读取数据，如本地数据不存在，则通过url爬取
     * @param url
     */
    @Override
    public String crawMiGuMusic(String url) {
        logger.info("开始读取咪咕音乐热歌文件内容...");
        List<SongsBean> songsBeanList = songsRepository.selectSingsByUrl(url);

        if (songsBeanList == null || songsBeanList.isEmpty()) {
            logger.error("咪咕音乐热歌榜功能可能存在某种异常，请稍后再试");
        }

        StringBuilder context = new StringBuilder("");
        assert songsBeanList != null;
        for (SongsBean songsBean : songsBeanList) {
            context.append("歌手名：")
                    .append(songsBean.getSingerName())
                    .append("\n")
                    .append("歌曲名：")
                    .append(songsBean.getSongName())
                    .append("\n");
        }

        return context.toString();
    }

}
