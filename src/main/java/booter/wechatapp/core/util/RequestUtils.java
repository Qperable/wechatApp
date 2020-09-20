package booter.wechatapp.core.util;

import booter.wechatapp.core.bean.BaseRequestBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;

/**
 * 解析请求相关内容
 *
 * @author wuyachong
 * @date 20200705
 */
public class RequestUtils {

    private static Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    private static String TOKEN = "123456";

    /**
     * 从请求中获取 xml 信息
     * @param request
     * @param requestType
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static BaseRequestBean baseMsgBean(HttpServletRequest request, String requestType) throws IOException, DocumentException {
        // 获取HTTP请求的输入流
        // 已HTTP请求输入流建立一个BufferedReader对象
        BufferedReader br = new BufferedReader(new InputStreamReader(
                request.getInputStream(), "UTF-8"));
        String buffer;
        // 存放请求内容
        StringBuffer xml = new StringBuffer();
        while ((buffer = br.readLine()) != null) {
            // 在页面中显示读取到的请求参数
            xml.append(buffer);
        }

        String callbackMessage = xml.toString();
        BaseRequestBean payment = null;
        if (StringUtils.isNotEmpty(callbackMessage)) {
            payment = new BaseRequestBean();
            Document doc = null;
            //1.读取并解析XML文档,SAXReader就是一个管道，用一个流的方式，把xml文件读出来
            // SAXReader reader = new SAXReader();
            // Document document = reader.read(new File("User.hbm.xml")); //User.hbm.xml表示你要解析的xml文档
            //2.将字符串转为XML
            doc = DocumentHelper.parseText(callbackMessage);
            Element rootElt = doc.getRootElement(); // 获取根节点Request
            logger.info("根节点：" + rootElt.getName()); // 拿到根节点的名称
            Iterator iter = rootElt.elementIterator("Head"); // 获取根节点下的子节点head
            // 遍历head节点
            while (iter.hasNext()) {
                logger.info("获取消息头内容");
                Element recordEless = (Element) iter.next();

                String toUserName = recordEless.elementTextTrim("ToUserName");
                String fromUserName = recordEless.elementTextTrim("FromUserName");
                String createTime = recordEless.elementTextTrim("CreateTime");
                String msgType = recordEless.elementTextTrim("MsgType");
                String msgId = recordEless.elementTextTrim("MsgId");

                payment.setToUserName(toUserName);
                payment.setFromUserName(fromUserName);
                payment.setCreateTime(createTime);
                payment.setMsgType(msgType);
                payment.setMsgId(msgId);
            }
            Iterator iterss = rootElt.elementIterator("Body"); ///获取根节点下的子节点body
            // 遍历body节点
            while (iterss.hasNext()) {
                logger.info("获取消息体内容");
            }
        }

        return payment;
    }

    /**
     * 请求校验
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature,
                                         String timestamp,
                                         String nonce) {
        //排序
        String sortString = sort(TOKEN, timestamp, nonce);
        //加密
        String myString = sha1(sortString);
        //校验
        if (myString != null && myString.equals(signature)) {
            //如果检验成功原样返回echostr，微信服务器接收到此输出，才会确认检验完成。
            return true;
        } else {
            return false;
        }
    }

    private static String sort(String token,
                               String timestamp,
                               String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }

        return sb.toString();
    }

    private static String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
