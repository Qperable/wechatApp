package core.matcher;

import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.bean.WxXmlMessage;
import core.constants.MenuKey;

/**
 * 功能描述：响应帮助栏，判断是否回复
 *
 * @Author: wuyachong
 * @Date: 2020/9/11
 */

public class HelpDocMatcher implements WxMessageMatcher {

    @Override
    public boolean match(WxXmlMessage message) {
        if (MenuKey.HELP.equals(message.getEventKey())) {
            return true;
        }
        return false;
    }

}