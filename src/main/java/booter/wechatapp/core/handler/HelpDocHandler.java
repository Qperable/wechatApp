package booter.wechatapp.core.handler;

import booter.wechatapp.core.constants.ResponseConstant;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;
import booter.wechatapp.core.constants.MenuKeyConstant;

import java.util.Map;

/**
 * 功能描述：帮助菜单回复类
 *
 * @author wuyachong
 * @date 2020/09/07
 */
public class HelpDocHandler implements WxMessageHandler {

    private static HelpDocHandler instance = null;

    private boolean isRun = false;

    private HelpDocHandler(){}

    public static synchronized HelpDocHandler getInstance(){
        if (instance == null) {
            instance = new HelpDocHandler();
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
        if (MenuKeyConstant.HELP.equals(wxMessage.getEventKey())) {
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

