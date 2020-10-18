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

    @Value("${crawler.local}")
    private String local;

    /**
     * 配置文件读取后该配置类在某些地方由于不明原因不能直接通过@resource获取参数值
     * 只能先暂时另添一个静态变量，在初始化的时候放进去
     */
    private String staticLocal;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getStaticLocal() {
        return staticLocal;
    }

    public void setStaticLocal(String staticLocal) {
        this.staticLocal = staticLocal;
    }

    @Override
    public String toString() {
        return "CrawlerConfig{" +
                "local='" + local + '\'' +
                '}';
    }
}
