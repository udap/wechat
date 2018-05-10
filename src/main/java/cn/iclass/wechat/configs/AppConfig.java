package cn.iclass.wechat.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaRepositories(value = "cn.iclass")
@PropertySources(@PropertySource(value = "classpath:config/error.properties", ignoreResourceNotFound = false, encoding = "UTF-8"))
@Configuration
@EnableSwagger2
public class AppConfig {
    public AppConfig() {
    }

    /*@Bean
    public Docket appApi() {
        return (new Docket(DocumentationType.SWAGGER_2))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }*/
}