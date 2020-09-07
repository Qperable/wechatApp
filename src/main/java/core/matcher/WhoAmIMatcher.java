package core.matcher;

import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.util.StringUtils;

/**
 * 功能描述：响应消息，判断是否回复
 *
 * @author wuyachong
 * @date 2020/07/09
 */
public class WhoAmIMatcher implements WxMessageMatcher {

    @Override
    public boolean match(WxXmlMessage message) {
        if (StringUtils.isNotEmpty(message.getContent())) {
            if (message.getContent().equals("我是谁")) {
                return true;
            }
        }
        return false;
    }

}
