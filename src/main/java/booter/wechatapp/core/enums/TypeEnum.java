package booter.wechatapp.core.enums;

/**
 * 功能描述：类型枚举类
 *
 * @author wuyachong
 * @date 2020/09/09
 */
public enum TypeEnum {
    /** 文本类型 */
    TEXT("1","text"),
    /** 图片类型 */
    IMAGE("2", "image"),
    /** 语音类型 */
    VOICE("3", "voice"),
    /** 视频类型 */
    VIDEO("4", "video"),
    /** 短视频类型 */
    SHORT_VIDEO("5", "shortvideo"),
    /** 地理位置类型 */
    LOCATION("6", "location"),
    /** 链接类型 */
    LINK("7", "link");


    TypeEnum(String code, String type) {
        this.code = code;
        this.type = type;
    }

    private String code;
    private String type;

    public String getCode() {return code;}
    public String getType() {return type;}

    public static TypeEnum getByCode(String code) {
        for (TypeEnum value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
