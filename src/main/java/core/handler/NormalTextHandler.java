package core.handler;

import core.constants.ResponseConstant;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;
import core.constants.RequestConstant;

import java.util.Map;

/**
 * 功能描述：消息回复实现类
 *
 * @author wuyachong
 * @date 2020/09/07
 */
public class NormalTextHandler implements WxMessageHandler {

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
        String msg = wxMessage.getContent();
        String content;
        switch (msg) {
            case RequestConstant.WHO_AM_I:
                content = wxMessage.getFromUserName();
                break;
            default:
                content = ResponseConstant.HELP;
        }
        return WxXmlOutMessage.TEXT().content(content)
                .toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
    }
}

