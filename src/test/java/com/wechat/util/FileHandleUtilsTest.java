package com.wechat.util;

import booter.wechatapp.core.properties.CrawlerProperties;
import booter.wechatapp.core.util.FileHandleUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 功能描述：文件处理测试类
 *
 * @Author: wuyachong
 * @Date: 2020/9/20
 */

@EnableConfigurationProperties(CrawlerProperties.class)
@Component
public class FileHandleUtilsTest {

    @Resource
    private FileHandleUtils fileHandleUtils;

    @Test
    public void getCrawlerPropertiesTest() {
        System.out.println(fileHandleUtils);
    }

    @Test
    public void assembleTextNameTest() {
        String textName = fileHandleUtils.assembleTextName("https://music.migu.cn/v3/music/top/jianjiao_newsong");
        System.out.println(textName);
    }
}
