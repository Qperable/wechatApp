package core.handler;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;
import core.constants.CrawlerUrlConstant;
import core.interfaces.CrawlerUseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 功能描述：热歌菜单回复类
 *
 * @author wuyachong
 * @date 2020/09/11
 */
@Component
public class HotSongsHandler implements WxMessageHandler {

    @Resource
    private CrawlerUseService crawlerUseService;

    private static HotSongsHandler instance = null;

    private boolean isRun = false;

    private Logger logger = LoggerFactory.getLogger(HotSongsHandler.class);

    private synchronized  boolean getIsRun() {
        return isRun;
    }

    private synchronized void setRun(boolean run) {
        isRun = run;
    }


    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, IService iService) throws WxErrorException {
        WxXmlOutMessage response = null;
        if (CrawlerUrlConstant.MI_GU_TOP_NEW_SONG_URL.equals(wxMessage.getEventKey())) {
            setRun(true);
            response = execute(wxMessage);
            setRun(false);
        }
        logger.info("咪咕新歌榜返回给用户的信息：" + response);
        return response;
    }

    private WxXmlOutMessage execute(WxXmlMessage wxMessage) {
        String context = crawlerUseService.crawMiGuMusic(wxMessage.getEventKey());

        return WxXmlOutMessage.TEXT().content(context)
                .toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
    }
}

