package core.bean;

import java.io.Serializable;

/**
 * 消息接收父类bean
 * @author wuyachong
 * @date 20200705
 */
public class BaseRequestBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 开发者微信号
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     */
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     */
    private String fromUserName;

    /**
     * 消息创建时间 （整型）
     * <CreateTime>1348831860</CreateTime>
     */
    private String createTime;

    /**
     * 消息类型，文本为text
     * <MsgType><![CDATA[text]]></MsgType>
     */
    private String msgType;

    /**
     * 消息id，64位整型
     * <MsgId>1234567890123456</MsgId>
     */
    private String msgId;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
