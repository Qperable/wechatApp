package core.handler;

import core.constants.CrawlerUrlConstant;
import core.service.CrawlerUseServiceImpl;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;

import java.util.Map;

/**
 * 功能描述：热歌菜单回复类
 *
 * @author wuyachong
 * @date 2020/09/11
 */
public class HotSongsHandler implements WxMessageHandler {

    private static HotSongsHandler instance = null;

    private boolean isRun = false;

    private HotSongsHandler(){}

    public static synchronized HotSongsHandler getInstance(){
        if (instance == null) {
            instance = new HotSongsHandler();
        }
        return instance;
    }

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
        return response;
    }

    private WxXmlOutMessage execute(WxXmlMessage wxMessage) {
        CrawlerUseServiceImpl crawlerUseService = new CrawlerUseServiceImpl();
        String context = crawlerUseService.crawMiGuMusic(wxMessage.getEventKey());

        return WxXmlOutMessage.TEXT().content(context)
                .toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
    }
}

