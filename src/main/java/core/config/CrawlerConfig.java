package core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 功能描述：爬虫工具配置类
 *
 * @Author: wuyachong
 * @Date: 2020/9/20
 */

@Configuration
@ConfigurationProperties(prefix = "crawler", ignoreUnknownFields = false)
@PropertySource(value = {"classpath:config/util.properties"}, encoding = "utf-8")
public class CrawlerConfig {

    /**
     * 菜单开关
     */
    @Value("${crawler.iSwitch}")
    private boolean iSwitch;

    /**
     * 文件本地存放路径
     */
    @Value("${crawler.local}")
    private String local;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public boolean isiSwitch() {
        return iSwitch;
    }

    public void setiSwitch(boolean iSwitch) {
        this.iSwitch = iSwitch;
    }

    @Override
    public String toString() {
        return "CrawlerConfig{" +
                "iSwitch=" + iSwitch +
                ", local='" + local + '\'' +
                '}';
    }
}
