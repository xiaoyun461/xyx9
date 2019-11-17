package com.xy.xyv9search;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class XyV9SearchApplicationTests {

    @Test
    void contextLoads() {
        List str = Arrays.asList("wa", "awd", "fdf", "Erwer");
        Page<String> objectPage = new Page<>().addOrder(str);
        objectPage.setTotal(str.size());
        objectPage.setSize(1);
        System.out.println(objectPage.getPages());
        System.out.println(objectPage.getCurrent());
        System.out.println(objectPage.getSize());
        System.out.println(objectPage.getTotal());
        System.out.println(objectPage.getRecords());
        System.out.println(objectPage.hasNext());
        System.out.println(objectPage.getOrders());


    }

}
