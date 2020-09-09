package core.service;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxXmlMessage;
import core.constants.MenuKey;
import core.handler.HelpDocHandler;
import core.handler.NormalTextHandler;
import core.matcher.NormalTextMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 功能描述：微信路由处理类
 * @author wuyachong
 * @date 20200705
 */
@Service
public class WechatRouterDealService {

    private static Logger logger = LoggerFactory.getLogger(WechatRouterDealService.class);

    private static WxMessageRouter wxMessageRouter;

    public WxMessageRouter instanceRouter() {
        if (wxMessageRouter == null) {
            wxMessageRouter = routerCreate();
        }
        return wxMessageRouter;
    }

    private WxMessageRouter routerCreate() {
        // 创建一个路由器
        IService iService = new WxService();
        WxMessageRouter router = new WxMessageRouter(iService);
        router.rule().msgType(WxConsts.XML_MSG_TEXT).matcher(new NormalTextMatcher()).handler(new NormalTextHandler()).end()
                .rule().event(WxConsts.EVT_CLICK).eventKey(MenuKey.HELP).handler(HelpDocHandler.getInstance()).end();
        return router;
    }
}
