package com.xy.xyv9searchservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.xy.v9.mapper")
public class XyV9SearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyV9SearchServiceApplication.class, args);
    }

}
