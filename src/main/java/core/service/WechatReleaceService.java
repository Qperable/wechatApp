package core.service;

import com.soecode.wxtools.bean.WxXmlMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 微信消息连接请求接收类
 * @author wuyachong
 * @date 20200705
 */
@Service
public class WechatReleaceService {

    private static Logger logger = LoggerFactory.getLogger(WechatReleaceService.class);


    public void textMsgProcess(WxXmlMessage text) {
        System.out.println("这是一条文本消息");
    }
}
