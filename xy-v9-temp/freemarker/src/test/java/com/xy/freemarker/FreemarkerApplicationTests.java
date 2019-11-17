package com.xy.freemarker;

import com.xy.freemarker.entity.Product;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@SpringBootTest
class FreemarkerApplicationTests {
    @Autowired
    private Configuration configuration;

    @Test
    void contextLoads() throws IOException, TemplateException {
        Template template = configuration.getTemplate("freemarker.ftl");
        Map<String, Object> data = new HashMap<>();
        data.put("name", "xiaoyun461wawa");

        Product product = new Product();
        product.setId(1);
        product.setBirthday(new Date());
        product.setName("超强电风扇");
        data.put("product", product);

        Product product2 = new Product();
        product2.setId(2);
        product2.setBirthday(new Date());
        product2.setName("超强冰箱");
        List<Product> list = new ArrayList<>();
        list.add(product);
        list.add(product2);

        data.put("list", list);

        data.put("age", 18);

        FileWriter writer = new FileWriter(new File("F:\\java\\xyv9\\xy-v9-temp\\freemarker\\src\\main\\resources\\templates\\freemarker.html"));
        template.process(data, writer);
        System.out.println("生成静态页成功");


    }

}
