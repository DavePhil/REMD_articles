package com.remd.remd_articles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.remd.remd_articles")
@RefreshScope
public class RemdArticlesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemdArticlesApplication.class, args);
    }

}
