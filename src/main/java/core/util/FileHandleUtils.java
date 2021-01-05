package core.util;

import core.enums.UrlEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 功能描述：本地内容下载及读取工具类
 *
 * @Author: wuyachong
 * @Date: 2020/9/19
 */

public class FileHandleUtils {

    private static Logger logger = LoggerFactory.getLogger(FileHandleUtils.class);

    private static final String PARENT = "../";

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
        File absFile = new File(absoluteResource.getPath());
        String absWay = absoluteResource.getPath();
        // 去除路径中的 ../
        while (local.startsWith(PARENT)) {
            absWay = absFile.getParent();
            local = local.substring(local.indexOf("/"));
        }
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
        File fileRes = new File(fileName.substring(0, fileName.lastIndexOf("/")));
        OutputStreamWriter outputStreamWriter = null;
        if (mapFile.exists()) {
            return;
        } else if (!fileRes.exists()) {
            logger.error("该文件路径不存在：" + fileRes.getName());
            if (fileRes.mkdirs()) {
                logger.info("创建文件路径成功");
            } else {
                logger.error("创建文件路径失败");
            }
        } else {
            logger.info("文件目录存在，准备创建文件中...");
        }
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(mapFile), StandardCharsets.UTF_8);
            if (mapFile.createNewFile()) {
                logger.info("文件：" + fileName + "创建成功");
            } else {
                logger.info("请检查文件：" + fileName + "是否已存在");
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

    /**
     * 读取预下载爬虫信息
     *
     * @param fileName
     */
    public static String fileReadByMap(String fileName) {
        File mapFile = new File(fileName);
        InputStreamReader inputStreamReader = null;
        String fileContext = "";
        try {
            inputStreamReader  = new InputStreamReader(new FileInputStream(mapFile), StandardCharsets.UTF_8);
            char[] context = new char[2048];
            int read = inputStreamReader.read(context);
            fileContext = new String(context, 0, read);
            logger.info("本地文件数据获取：" + fileContext);
        } catch (Exception e) {
            logger.error("该文件不存在，待下载：" + fileName);
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
