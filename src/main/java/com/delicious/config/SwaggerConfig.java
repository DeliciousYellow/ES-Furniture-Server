package com.delicious.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @program: ES-furniture
 * @description: swagger配置类
 * @author: 王炸！！
 * @create: 2023-03-16 13:57
 **/
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build().groupName("es-furniture");
    }
    /**
     * 页面基础信息
     */
    private ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact("delicious", "https://www.baidu.com", "deliciousjsp@gmail.com");
        return new ApiInfo(
                "易卖家具的Swagger3接口文档",
                "易卖家具的接口生成文档",
                "1.0",
                "https://www.baidu.com/",
                contact,
                "Apache 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}