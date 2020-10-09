package core.properties;

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
public class CrawlerProperties {

    @Value("${crawler.local}")
    private String local;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
