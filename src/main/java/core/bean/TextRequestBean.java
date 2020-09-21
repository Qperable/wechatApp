package core.bean;

/**
 * 文本类消息接收类bean
 * @author wuyachong
 * @date 20200705
 */
public class TextRequestBean extends BaseRequestBean {

    /**
     * 文本消息内容
     * <Content><![CDATA[this is a test]]></Content>
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MsgRequestBean{" +
                "toUserName='" + super.getToUserName() + '\'' +
                ", fromUserName='" + super.getFromUserName() + '\'' +
                ", createTime='" + super.getCreateTime() + '\'' +
                ", msgType='" + super.getMsgType() + '\'' +
                ", msgId='" + super.getMsgId() + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
