package core.matcher;

import core.constants.CrawlerUrlConstant;
import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.bean.WxXmlMessage;

/**
 * 功能描述：响应热歌栏，判断是否回复
 *
 * @Author: wuyachong
 * @Date: 2020/9/11
 */

public class HotSongsMatcher implements WxMessageMatcher {

    @Override
    public boolean match(WxXmlMessage message) {
        if (CrawlerUrlConstant.MI_GU_TOP_NEW_SONG_URL.equals(message.getEventKey())) {
            return true;
        }
        return false;
    }

}
