package com.xy.xyv9itemservice;

import com.xy.api.item.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class XyV9ItemServiceApplicationTests {

    @Autowired
    private ItemService itemService;

    @Test
    void contextLoads() {
        List<Long> ids = new ArrayList<>();
        for (long i = 1; i < 26; i++) {
            ids.add(i);
        }
        itemService.batchCreateHtml(ids);
    }

}
