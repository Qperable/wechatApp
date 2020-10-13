package core.service;

import core.bean.ContextParseBean;
import core.enums.UrlEnum;
import core.config.CrawlerConfig;
import core.util.ContextParseUtils;
import core.util.FileHandleUtils;
import core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.lang.reflect.Method;
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
@Component
public class CrawlerPipelineService implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(CrawlerContextService.class);

    @Resource
    private CrawlerConfig crawlerConfig;

    @Override
    public void process(ResultItems resultItems, Task task) {
        String url = resultItems.getRequest().getUrl();
        logger.info("get page: " + url);
        String methodName = UrlEnum.getMethodByUrl(url);
        if (methodName == null) {
            logger.error("该爬虫链接：" + url + "不存在，请在常量类和枚举中补充");
        } else {
            // 反射调用url对应的爬虫方法
            try {
                Class<?> clazz = Class.forName("CrawlerPipelineService");
                Method method = clazz.getDeclaredMethod(methodName, ResultItems.class);
                method.invoke(clazz.newInstance(), resultItems);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 咪咕音乐尖叫新歌榜
     * @param resultItems
     */
    private void crawMiGuNewTopSong(ResultItems resultItems) {
        String url = resultItems.getRequest().getUrl();
        Map<String, String> songMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            logger.info(entry.getKey() + ":\t" + entry.getValue());
            String value = entry.getValue().toString();
            String[] entryContext = value.substring(1, value.length()-1).split(", ");
            List<String> songList = new ArrayList<>();
            List<String> artistList = new ArrayList<>();
            Map<String, String> classMap;
            String href;
            String context;
            ContextParseBean contextParseBean;
            for (String str : entryContext) {
                contextParseBean = ContextParseUtils.transMiGuContextTobean(str);
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
        FileHandleUtils.fileDownloadByMap(songMap, FileHandleUtils.assembleTextName(crawlerConfig.getLocal(), url));
    }

}
