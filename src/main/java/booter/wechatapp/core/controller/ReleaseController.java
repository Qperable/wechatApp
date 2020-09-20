package booter.wechatapp.core.controller;

import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.util.xml.XStreamTransformer;
import booter.wechatapp.core.service.WechatRouterDealService;
import booter.wechatapp.core.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 接收微信连接建立控制类
 *
 * @author wuyachong
 * @date 20200705
 */
@RestController
public class ReleaseController {

    private static Logger logger = LoggerFactory.getLogger(ReleaseController.class);

    @Resource
    private WechatRouterDealService wechatRouterDealService;

    @GetMapping
    public String testMsgGet(@RequestParam("signature") String signature,
                             @RequestParam("timestamp") String timestamp,
                             @RequestParam("nonce") String nonce,
                             @RequestParam("echostr") String echostr) throws IOException {
        logger.info("接收到一条新消息:" + signature);
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (RequestUtils.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }

        return "";
    }

    @PostMapping
    public void handle(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // 微信服务器推送过来的是XML格式。
            WxXmlMessage wx = XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
            logger.info("消息：\n " + wx.toString());
            // 创建一个路由器
            WxMessageRouter router = wechatRouterDealService.instanceRouter();
            // 把消息传递给路由器进行处理
            WxXmlOutMessage xmlOutMsg = router.route(wx);
            if (xmlOutMsg != null) {
                // 因为是明文，所以不用加密，直接返回给用户
                out.print(xmlOutMsg.toXml());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
