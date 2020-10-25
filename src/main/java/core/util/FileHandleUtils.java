package core.util;

import core.enums.UrlEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.Map;

/**
 * 功能描述：本地内容下载及读取工具类
 *
 * @Author: wuyachong
 * @Date: 2020/9/19
 */

public class FileHandleUtils {

    private static Logger logger = LoggerFactory.getLogger(FileHandleUtils.class);

    /**
     * 通过url获取文件名称
     * @param url
     * @return
     */
    public static String assembleTextName(String local, String url) {
        String cnName = UrlEnum.getCnNameByUrl(url);
        // 获取的是该用户目录资源存放地址 如：file:/data/workspace/wechat-1.0/config/
        URL absoluteResource = Thread.currentThread().getContextClassLoader().getResource("");
        assert absoluteResource != null;
        String absWay = absoluteResource.getPath();
        return  absWay + local + cnName + DateUtils.getCurrentTimeYMDH() + ".txt";
    }

    /**
     * 将map内容固化到本地
     *
     * @param map
     * @param fileName
     */
    public static void fileDownloadByMap(Map<String, String> map, String fileName) {
        File mapFile = new File(fileName);
        OutputStreamWriter outputStreamWriter = null;
        if (mapFile.exists()) {
            return;
        }
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(mapFile));
            boolean newFile = mapFile.createNewFile();
            if (newFile) {
                logger.info("文件：" + fileName + "创建成功");
            } else {
                logger.info("文件：" + fileName + "创建失败");
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                stringBuilder.append(entry.getKey())
                        .append(" : ")
                        .append(entry.getValue())
                        .append('\n');
            }
            outputStreamWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(outputStreamWriter);
        }
    }

    public static String fileReadByMap(String fileName) {
        File mapFile = new File(fileName);
        InputStreamReader inputStreamReader = null;
        String fileContext = "";
        try {
            inputStreamReader  = new InputStreamReader(new FileInputStream(mapFile), "GBK");
            char[] context = new char[1024];
            int read = inputStreamReader.read(context);
            fileContext = new String(context, 0, read);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(inputStreamReader);
        }
        return fileContext;
    }

    /**
     * 关闭文件流
     * @param stream
     */
    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
