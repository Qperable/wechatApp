package core.matcher;

import core.enums.TypeEnum;
import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.util.StringUtils;

/**
 * 功能描述：响应消息，判断是否回复
 *
 * @author wuyachong
 * @date 2020/07/09
 */
public class NormalTextMatcher implements WxMessageMatcher {

    @Override
    public boolean match(WxXmlMessage message) {
        if (TypeEnum.TEXT.getType().equals(message.getMsgType())) {
            if (StringUtils.isNotEmpty(message.getContent())) {
                return true;
            }
        }
        return false;
    }

}
