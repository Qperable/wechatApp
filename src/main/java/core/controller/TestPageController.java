package core.controller;

import com.alibaba.fastjson.JSON;
import core.util.PackageFileNameUtils;
import core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static core.util.PackageFileNameUtils.addClass;

/**
 * 测试沙箱页面
 * @author wuyachong
 * @date 2020/11/30
 */
@Controller
public class TestPageController {

    private static Logger logger = LoggerFactory.getLogger(TestPageController.class);

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/test/queryAllInterfaceMethodName")
    public List<String> queryAllInterfaceMethodName() {
        List<String> interfaceMethodName = new ArrayList<>();
        Map<String, List<String>> interfaceMap = addClass();
        String methodList;
        for (Map.Entry<String, List<String>> entry : interfaceMap.entrySet()) {
            for (String method : entry.getValue()) {
                methodList = entry.getKey() + "." + method;
                interfaceMethodName.add(methodList);
            }
        }
        return interfaceMethodName;
    }

    @RequestMapping("/test/testAction")
    public void testAction(@RequestParam("interfaceName") String interfaceName, @RequestParam("requestBody") String requestBody) {
        logger.info("测试功能传入参数：{" + interfaceName + ", " + requestBody + "}");
        Map<String, Object> parameter = new HashMap<>(16);
        if (StringUtils.isNotEmpty(requestBody)) {
            parameter = JSON.parseObject(requestBody, Map.class);
        }
        int index = interfaceName.indexOf(".");
        String faceName = interfaceName.substring(0, index);
        String methodName = interfaceName.substring(index+1);
        try {
            Class<?> faceClazz = Class.forName(PackageFileNameUtils.PACKAGE_PATH + "." + faceName);
            Object instance = faceClazz.newInstance();
            // 获取入参数组
            Object[] paramCollection = parameter.values().toArray();

            // 编辑测试入参Class
            List<Class> classList = new ArrayList<>();
            if (paramCollection.length > 0) {
                for (Object paramInstance : paramCollection) {
                    classList.add(paramInstance.getClass());
                }
            }
            Class[] paramArr = classList.toArray(new Class[0]);

            // 执行待测试方法
            Method method = faceClazz.getDeclaredMethod(methodName, paramArr);
            method.invoke(instance, paramCollection);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
