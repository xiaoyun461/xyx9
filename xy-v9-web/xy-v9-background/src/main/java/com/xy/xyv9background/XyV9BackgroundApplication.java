package com.xy.xyv9background;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDubbo
@Import(FdfsClientConfig.class)
public class XyV9BackgroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyV9BackgroundApplication.class, args);
    }

}
