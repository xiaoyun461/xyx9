package com.xy.xyv9itemservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.xy.v9.mapper")
public class XyV9ItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyV9ItemServiceApplication.class, args);
    }

}
