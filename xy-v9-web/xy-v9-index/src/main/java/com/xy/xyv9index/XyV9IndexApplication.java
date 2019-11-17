package com.xy.xyv9index;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class XyV9IndexApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyV9IndexApplication.class, args);
    }

}
