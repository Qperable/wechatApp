package core.service;

import core.bean.ContextParseBean;
import core.util.ContextParseUtils;
import core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：爬取内容处理类
 *
 * @Author: wuyachong
 * @Date: 2020/9/16
 */

public class CrawlerPipelineService implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(CrawlerContextService.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("get page: " + resultItems.getRequest().getUrl());
        Map<String, String> songMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            logger.info(entry.getKey() + ":\t" + entry.getValue());
            String[] entryContext = entry.getValue().toString().split(", ");
            List<String> songList = new ArrayList<>();
            List<String> artistList = new ArrayList<>();
            Map<String, String> classMap;
            String href;
            String context;
            ContextParseBean contextParseBean;
            for (String str : entryContext) {
                contextParseBean = ContextParseUtils.transDoubleContextTobean(str);
                classMap = contextParseBean.getClassMap();
                if (classMap != null) {
                    context = StringUtils.trimNull(contextParseBean.getContext(), classMap.get("title"));
                    href = classMap.get("href");
                    if (href == null) {
                        continue;
                    } if (href.startsWith("/v3/music/song") && StringUtils.isNotEmpty(context)) {
                        songList.add(contextParseBean.getContext());
                    } else if (href.startsWith("/v3/music/artist")) {
                        artistList.add(contextParseBean.getContext());
                    }
                }
            }

            if (songList.size() == artistList.size()) {
                for (int i = 0; i < songList.size(); i++) {
                    songMap.put(songList.get(i), artistList.get(i));
                }
            } else {
                // TODO: 2020/9/18 抛出异常，歌名和歌手不匹配
                logger.error("歌名和歌手不匹配");
            }
        }
        logger.info("获取到的歌单：" + songMap.toString());
    }

}
