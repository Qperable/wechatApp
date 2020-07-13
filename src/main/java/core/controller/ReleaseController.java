package core.controller;

import core.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 接收微信连接建立控制类
 *
 * @author wuyachong
 * @date 20200705
 */
@RestController
public class ReleaseController {

    private static Logger logger = LoggerFactory.getLogger(ReleaseController.class);

    @Autowired


    @RequestMapping
    public boolean ensureConnection(HttpServletRequest request) {
        logger.info("接收到一条新消息，走的是固定返回失败的路径");
        return false;
    }

    @GetMapping("/sell/test")
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
}
