package core.handler;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;
import core.constants.ResponseConstant;

import java.util.Map;

/**
 * 功能描述：消息回复实现类
 *
 * @author wuyachong
 * @date 2020/09/07
 */
public class WhoAmIHandler implements WxMessageHandler {

    private boolean isRun = false;

    private synchronized  boolean getIsRun() {
        return isRun;
    }

    private synchronized void setRun(boolean run) {
        isRun = run;
    }


    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, IService iService) throws WxErrorException {
        WxXmlOutMessage response = null;
        if (!getIsRun()) {
            setRun(true);
            response = execute(wxMessage);
            setRun(false);
        }
        return response;
    }

    private WxXmlOutMessage execute(WxXmlMessage wxMessage) {
        return WxXmlOutMessage.TEXT().content(ResponseConstant.HELP).toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
    }
}

