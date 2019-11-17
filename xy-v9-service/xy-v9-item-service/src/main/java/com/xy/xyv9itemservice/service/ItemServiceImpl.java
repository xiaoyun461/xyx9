package com.xy.xyv9itemservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.xy.api.item.ItemService;
import com.xy.v9.common.pojo.ResultBean;
import com.xy.v9.entity.Product;
import com.xy.v9.mapper.ProductMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired(required = false)
    private ProductMapper productMapper;
    @Autowired
    private Configuration configuration;

    //    private ExecutorService pool = Executors.newSingleThreadExecutor();
//    private ExecutorService pool2 = Executors.newFixedThreadPool(10);
//    private ExecutorService pool3 = Executors.newCachedxPool();

    private int corePoolSize = Runtime.getRuntime().availableProcessors();

    private ExecutorService pool = new ThreadPoolExecutor(corePoolSize, corePoolSize * 2, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));

    @Value("${html.locations}")
    private String htmlLocations;


    @Override
    public ResultBean createHtmlById(Long productId) {
        Product product = productMapper.selectById(productId);
        try {
            Template template = configuration.getTemplate("item.ftl");
            Map<String, Object> data = new HashMap<>();
            data.put("product", product);
            FileWriter writer = new FileWriter(htmlLocations + productId + ".html");
            template.process(data, writer);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            return ResultBean.error("生成静态页面失败");
        }
        return ResultBean.success("生成静态页面成功");
    }

    @Override
    public ResultBean batchCreateHtml(List<Long> idList) {
        List<Future> list = new ArrayList<>(idList.size());
        for (Long id : idList) {
            list.add(pool.submit(new CreateHtmlTask(id)));
        }
        for (Future future : list) {
            try {
                log.info("{}", future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return ResultBean.success("批量生成页面成功");
    }

    private class CreateHtmlTask implements Callable<Boolean> {

        private Long productId;

        public CreateHtmlTask(Long productId) {
            this.productId = productId;
        }

        @Override
        public Boolean call() throws Exception {
            Product product = productMapper.selectById(productId);
            try {
                Template template = configuration.getTemplate("item.ftl");
                Map<String, Object> data = new HashMap<>();
                data.put("product", product);
                FileWriter writer = new FileWriter(htmlLocations + productId + ".html");
                template.process(data, writer);
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
}
