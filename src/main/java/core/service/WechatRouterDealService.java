package core.service;

import core.handler.HelpDocHandler;
import core.handler.HotSongsHandler;
import core.matcher.HotSongsMatcher;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.api.WxService;
import core.handler.NormalTextHandler;
import core.matcher.HelpDocMatcher;
import core.matcher.NormalTextMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 功能描述：微信路由处理类
 * @author wuyachong
 * @date 20200705
 */
@Service
public class WechatRouterDealService {

    private static Logger logger = LoggerFactory.getLogger(WechatRouterDealService.class);

    @Resource
    private NormalTextHandler normalTextHandler;
    @Resource
    private NormalTextMatcher normalTextMatcher;
    @Resource
    private HotSongsMatcher hotSongsMatcher;
    @Resource
    private HotSongsHandler hotSongsHandler;
    @Resource
    private HelpDocMatcher helpDocMatcher;
    @Resource
    private HelpDocHandler helpDocHandler;

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
        router.rule().msgType(WxConsts.XML_MSG_TEXT).matcher(normalTextMatcher).handler(normalTextHandler).end()
                .rule().event(WxConsts.EVT_CLICK).matcher(hotSongsMatcher).handler(hotSongsHandler).next()
                .rule().event(WxConsts.EVT_CLICK).matcher(helpDocMatcher).handler(helpDocHandler).end();
        return router;
    }
}
