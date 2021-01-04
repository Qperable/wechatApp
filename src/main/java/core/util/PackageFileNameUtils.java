package core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取某个包下所有类名工具类
 * @author wuyachong
 * @date 2020/11/30
 */
public class PackageFileNameUtils {

    private static final Logger log = LoggerFactory.getLogger(PackageFileNameUtils.class);

    private static Map<String, List<String>> map = new HashMap<>();

    /**
     * 默认使用的类加载器
     */
    private static ClassLoader classLoader = PackageFileNameUtils.class.getClassLoader();

    /**
     * 需要扫描的包名
     */
    public static final String PACKAGE_PATH = "core.service";

    public static void main(String[] args) {
        addClass();
    }

    /**
     * 获取包下所有类并加入map
     */
    public static Map<String, List<String>> addClass() {
        URL url = classLoader.getResource(PACKAGE_PATH.replace(".", "/"));
        assert url != null : "测试路径不存在";
        String protocol = url.getProtocol();
        if ("file".equals(protocol)) {
            // 查找配置路径下的接口及方法名
            try {
                PackageFileNameUtils.class.newInstance().findClassLocal(PACKAGE_PATH);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            log.error("获取接口路径有误：" + url.getPath());
        }
        log.info(map.toString());

        return map;
    }

    /**
     * 本地查找文件名并获取{interfaceName:methods}集合
     *
     * @param packName
     */
    private void findClassLocal(final String packName) {
        URI url;
        try {
            url = classLoader.getResource(packName.replace(".", "/")).toURI();
        } catch (URISyntaxException e1) {
            throw new RuntimeException("未找到包名");
        }
        File file = new File(url);
        file.listFiles(chiFile -> {
            if (chiFile.isDirectory()) {
                findClassLocal(packName + "." + chiFile.getName());
            }
            if (chiFile.getName().endsWith(".class")) {
                Class<?> clazz = null;
                try {
                    clazz = classLoader.loadClass(packName + "." + chiFile.getName().replace(".class", ""));
                } catch (ClassNotFoundException e) {
                    log.error("文件未找到");
                    e.printStackTrace();
                }
                String interfaceName = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
                Method[] methods = clazz.getMethods();
                List<String> list;
                list = map.computeIfAbsent(interfaceName, k -> new ArrayList<>());
                for (Method method : methods) {
                    list.add(method.getName());
                }
                return true;
            }
            return false;
        });
    }

}
