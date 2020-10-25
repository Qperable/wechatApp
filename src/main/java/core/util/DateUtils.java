package core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能描述：日期工具类
 *
 * @Author: wuyachong
 * @Date: 2020/9/19
 */

public class DateUtils {

    /**
     * 获取当前时间
     * 格式：年-月-日-时
     * @return
     */
    public static String getCurrentTimeYMDH() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMddHH");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
