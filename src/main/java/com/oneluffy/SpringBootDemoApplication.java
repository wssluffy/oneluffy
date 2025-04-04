package com.oneluffy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * <p>
 * SpringBoot启动类
 * </p>
 */
@SpringBootApplication
@EnableOpenApi
@MapperScan("com.oneluffy.mapper")
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

}
