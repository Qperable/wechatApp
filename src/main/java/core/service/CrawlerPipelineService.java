package core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

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
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            logger.info(entry.getKey() + ":\t" + entry.getValue());
        }
    }

}
