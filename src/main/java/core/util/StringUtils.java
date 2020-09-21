package core.util;

/**
 * String相关工具类
 * @author wuyachong
 * @date 20200705
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     * @param value
     * @return
     */
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean isEmpty(String value) {
        if (value != null && value.trim().length() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 按顺序取String值不为空的那一个
     * @param values
     * @return
     */
    public static String trimNull(String... values) {
        for (String value : values) {
            if (isNotEmpty(value)) {
                return value;
            }
        }
        return "";
    }

    /**
     * 将入参改为首字母大写
     * @param value
     * @return
     */
    public static String toUpperFirstCode(String value) {
        if (isNotEmpty(value)) {
            char[] valueChar = value.toCharArray();
            StringBuilder builder = new StringBuilder(valueChar[0]);
            for (int i = 1; i < valueChar.length; i++) {
                builder.append(valueChar);
            }
            return builder.toString();
        }

        return value;
    }
}
