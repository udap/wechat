package cn.iclass.wechat;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@EnableScheduling
@SpringBootApplication(scanBasePackages = "cn.iclass")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
