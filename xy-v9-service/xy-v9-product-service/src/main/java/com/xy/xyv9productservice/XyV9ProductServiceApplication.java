package com.xy.xyv9productservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
@MapperScan("com.xy.v9.mapper")
public class
XyV9ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyV9ProductServiceApplication.class, args);
    }

}
