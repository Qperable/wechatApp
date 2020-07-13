package booter.wechatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "core")
public class WechatAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatAppApplication.class, args);
    }

}

