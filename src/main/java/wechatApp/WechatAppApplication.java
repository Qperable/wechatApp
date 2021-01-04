package wechatApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"core","core.bean","core.config","core.service", "core.repository"})
public class WechatAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatAppApplication.class, args);
    }

}

