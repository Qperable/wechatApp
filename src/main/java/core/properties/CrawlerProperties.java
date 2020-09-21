package core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 功能描述：爬虫工具配置类
 *
 * @Author: wuyachong
 * @Date: 2020/9/20
 */

@Configuration
@ConfigurationProperties(prefix = "crawler", ignoreUnknownFields = false)
@PropertySource("classpath:config/util.properties")
@Component
public class CrawlerProperties {

    private String local;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
