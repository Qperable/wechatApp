package core.handler;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;
import core.constants.MenuKey;
import core.constants.ResponseConstant;

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
        if (MenuKey.HOT_SONG.equals(wxMessage.getEventKey())) {
            setRun(true);
            response = execute(wxMessage);
            setRun(false);
        }
        return response;
    }

    private WxXmlOutMessage execute(WxXmlMessage wxMessage) {
        return WxXmlOutMessage.TEXT().content(ResponseConstant.HELP)
                .toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
    }
}

